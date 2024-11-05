from flask import Flask, render_template, request, redirect, url_for
from datetime import datetime

app = Flask(__name__)

# Clases para las listas enlazadas

class NodoProyecto:
    def __init__(self, nombre, descripcion, fecha_inicio, fecha_fin, inversionistas, costos, electricidad_generada, monto_total):
        self.nombre = nombre
        self.descripcion = descripcion
        self.fecha_inicio = fecha_inicio
        self.fecha_fin = fecha_fin
        self.inversionistas = inversionistas
        self.costos = costos
        self.electricidad_generada = electricidad_generada
        self.monto_total = monto_total
        self.siguiente = None

class ListaEnlazadaProyectos:
    def __init__(self):
        self.cabeza = None

    def agregar(self, nombre, descripcion, fecha_inicio, fecha_fin, inversionistas, costos, electricidad_generada, monto_total):
        nuevo_proyecto = NodoProyecto(nombre, descripcion, fecha_inicio, fecha_fin, inversionistas, costos, electricidad_generada, monto_total)
        nuevo_proyecto.siguiente = self.cabeza
        self.cabeza = nuevo_proyecto

    def eliminar(self, nombre):
        actual = self.cabeza
        anterior = None
        while actual is not None:
            if actual.nombre == nombre:
                if anterior is None:
                    self.cabeza = actual.siguiente
                else:
                    anterior.siguiente = actual.siguiente
                return True
            anterior = actual
            actual = actual.siguiente
        return False

    def obtener_proyectos(self):
        proyectos = []
        actual = self.cabeza
        while actual is not None:
            proyectos.append(actual)
            actual = actual.siguiente
        return proyectos

class NodoTransaccion:
    def __init__(self, tipo, proyecto, fecha, detalles):
        self.tipo = tipo
        self.proyecto = proyecto
        self.fecha = fecha
        self.detalles = detalles
        self.siguiente = None

class ListaEnlazadaTransacciones:
    def __init__(self):
        self.cabeza = None

    def agregar(self, tipo, proyecto, detalles):
        nueva_transaccion = NodoTransaccion(tipo, proyecto, datetime.now().isoformat(), detalles)
        nueva_transaccion.siguiente = self.cabeza
        self.cabeza = nueva_transaccion

    def obtener_transacciones(self):
        transacciones = []
        actual = self.cabeza
        while actual is not None:
            transacciones.append(actual)
            actual = actual.siguiente
        return transacciones

# Instancias de las listas enlazadas
proyectos = ListaEnlazadaProyectos()
historial_transacciones = ListaEnlazadaTransacciones()

# Ruta principal
@app.route('/')
def index():
    return render_template('index.html')

# Ruta para agregar proyecto
@app.route('/agregar', methods=['GET', 'POST'])
def agregar_proyecto():
    if request.method == 'POST':
        proyectos.agregar(
            nombre=request.form['nombre'],
            descripcion=request.form['descripcion'],
            fecha_inicio=request.form['fechaInicioConstruccion'],
            fecha_fin=request.form['fechaFinConstruccion'],
            inversionistas=request.form['inversionistas'],
            costos=float(request.form['costos']),
            electricidad_generada=float(request.form['electricidadGenerada']),
            monto_total=float(request.form['montoTotal'])
        )
        
        historial_transacciones.agregar("Creación", request.form['nombre'], "Proyecto creado exitosamente.")
        return redirect(url_for('index'))
    return render_template('agregar.html')

# Ruta para eliminar proyecto
@app.route('/eliminar', methods=['GET', 'POST'])
def eliminar_proyecto():
    if request.method == 'POST':
        nombre = request.form['nombre']
        if proyectos.eliminar(nombre):
            historial_transacciones.agregar("Eliminación", nombre, "Proyecto eliminado.")
        return redirect(url_for('index'))
    return render_template('eliminar.html', proyectos=proyectos.obtener_proyectos())

# Ruta para mostrar proyectos
@app.route('/mostrar')
def mostrar_proyectos():
    return render_template('mostrar.html', proyectos=proyectos.obtener_proyectos())

# Ruta para ver historial de transacciones
@app.route('/historial_transacciones')
def historial_transacciones_view():
    return render_template('historial.html', transacciones=historial_transacciones.obtener_transacciones())

if __name__ == '__main__':
    app.run(debug=True)
