from flask import Flask, render_template, request, redirect, url_for
import json
import os

app = Flask(__name__)

JSON_FILE_PATH = '/home/leonardo/Escritorio/ProjectEnergy/rest/src/main/resources/proyectos.json'

def cargar_proyectos():
    if not os.path.exists(JSON_FILE_PATH):
        return []
    with open(JSON_FILE_PATH, 'r') as file:
        return json.load(file)

def guardar_proyectos(proyectos):
    with open(JSON_FILE_PATH, 'w') as file:
        json.dump(proyectos, file, indent=4)

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/agregar', methods=['GET', 'POST'])
def agregar_proyecto():
    if request.method == 'POST':
        proyectos = cargar_proyectos()
        nuevo_proyecto = {
            'nombre': request.form['nombre'],
            'descripcion': request.form['descripcion'],
            'fechaInicioConstruccion': request.form['fechaInicioConstruccion'],
            'fechaFinConstruccion': request.form['fechaFinConstruccion'],
            'inversionistas': request.form['inversionistas'],
            'costos': request.form['costos'],
            'electricidadGenerada': request.form['electricidadGenerada'],
            'montoTotal': request.form['montoTotal']
        }
        proyectos.append(nuevo_proyecto)
        guardar_proyectos(proyectos)
        return redirect(url_for('index'))
    return render_template('agregar.html')

@app.route('/mostrar', methods=['GET'])
def mostrar_proyectos():
    proyectos = cargar_proyectos()
    return render_template('mostrar.html', proyectos=proyectos)

@app.route('/eliminar', methods=['GET', 'POST'])
def eliminar_proyecto():
    proyectos = cargar_proyectos()
    if request.method == 'POST':
        proyectos = [p for p in proyectos if p['nombre'] != request.form['nombre']]
        guardar_proyectos(proyectos)
        return redirect(url_for('index'))
    return render_template('eliminar.html', proyectos=proyectos)

if __name__ == '__main__':
    app.run(debug=True)
