// API ENDPOINTS
const API = {
  empleados: '/empleados/listar',
  departamentos: '/departamentos/listar',
  vacacion: '/vacacion/listar',
  evaluaciones: '/evaluaciones/listar',
  asistencia: '/asistencia/listar',
  permisos: '/permisos/listar',
  horasextra: '/horasextra/listar',
  viatico: '/viatico/listar'
};

// MOSTRAR SECCIÓN + URL
function mostrarSeccion(id) {
  document.querySelectorAll('section').forEach(s => s.style.display = 'none');
  const seccion = document.getElementById(id);
  if (seccion) seccion.style.display = 'block';

  const rutas = {
    'empleados': '/empleados',
    'departamentos': '/departamentos',
    'vacacion': '/vacacion',
    'evaluaciones': '/evaluaciones',
    'asistencia': '/asistencia',
    'permisos': '/permisos',
    'horasextra': '/horasextra',
    'viatico': '/viatico',
  };
  history.pushState({ seccion: id }, '', rutas[id] || '/');

  listar(id);
  if (['empleados', 'vacacion', 'evaluaciones','asistencia', 'permisos', 'horasextra', 'viatico'].includes(id)) cargarSelects();
}

// LISTAR
async function listar(tipo) {
  try {
    const res = await fetch(API[tipo]);
    if (!res.ok) throw new Error(`HTTP ${res.status}`);
    const data = await res.json();

    const tablaId = `tabla${tipo.charAt(0).toUpperCase() + tipo.slice(1)}`;
    const tbody = document.querySelector(`#${tablaId} tbody`);
    if (!tbody) return;
    tbody.innerHTML = '';

    data.forEach(e => {
      let cols = '';
      if (tipo === 'empleados') {
        cols = `<td>${e.id}</td><td>${e.nombre}</td><td>${e.puesto}</td><td>${e.salario}</td><td>${e.departamento?.nombre || 'Sin depto'}</td>`;
      } else if (tipo === 'departamentos') {
        cols = `<td>${e.id}</td><td>${e.nombre}</td>`;
      } else if (tipo === 'vacacion') {
        cols = `<td>${e.id}</td><td>${e.empleado?.nombre || ''}</td><td>${e.fechaInicio || ''}</td><td>${e.fechaFin || ''}</td>`;
      } else if (tipo === 'evaluaciones') {
        cols = `<td>${e.id}</td><td>${e.empleado?.nombre || ''}</td><td>${e.resultado || ''}</td>`;
      }
      else if (tipo === 'asistencia') {
            cols = `<td>${e.id}</td>
                    <td>${e.empleado?.nombre || ''}</td>
                    <td>${e.fecha}</td>
                    <td>${e.horaEntrada}</td>
                    <td>${e.horaSalida}</td>`;
      }
      else if (tipo === 'permisos') {
          cols = `<td>${e.id}</td>
                  <td>${e.empleado?.nombre || ''}</td>
                  <td>${e.motivo}</td>
                  <td>${e.fechaInicio}</td>
                  <td>${e.fechaFin}</td>`;
      }
      else if (tipo === 'horasextra') {
          cols = `<td>${e.id}</td>
                  <td>${e.empleado?.nombre || ''}</td>
                  <td>${e.fecha}</td>
                  <td>${e.horas}</td>
                  <td>${e.motivo}</td>`;
      }
      else if (tipo === 'viatico') {
          cols = `<td>${e.id}</td>
                    <td>${e.empleado?.nombre || ''}</td>
                    <td>${e.fecha}</td>
                    <td>${e.monto}</td>
                    <td>${e.descripcion}</td>
                    <td>${e.aprobado ? 'Sí' : 'No'}</td>`;
          }

      tbody.innerHTML += `<tr>${cols}<td class="actions">
        <button onclick="editar('${tipo}', ${e.id})">Editar</button>
        <button class="delete" onclick="eliminar('${tipo}', ${e.id})">Eliminar</button>
      </td></tr>`;
    });
  } catch (error) {
    console.error('Error:', error);
    alert('Error al cargar datos');
  }
}

// GUARDAR
async function guardar(tipo, entidad, form) {
  const id = entidad.id;
  const method = id ? 'PUT' : 'POST';
  const url = id ? `/${tipo}/${id}` : `/${tipo}/registrar`;

  try {
    const res = await fetch(url, {
      method,
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(entidad)
    });

    if (res.ok) {
      form.reset();
      const idField = document.getElementById(`id${tipo.charAt(0).toUpperCase() + tipo.slice(1)}`);
      if (idField) idField.value = '';
      listar(tipo);
      if (tipo === 'departamentos' || tipo === 'empleados') cargarSelects();
      alert('Guardado');
    } else {
      alert('Error al guardar');
    }
  } catch (error) {
    alert('Error de red');
  }
}

// ELIMINAR
async function eliminar(tipo, id) {
  if (!confirm('¿Eliminar?')) return;
  const res = await fetch(`/${tipo}/${id}`, { method: 'DELETE' });
  if (res.ok) {
    listar(tipo);
    if (tipo === 'departamentos' || tipo === 'empleados') cargarSelects();
  }
}

// EDITAR
async function editar(tipo, id) {
  const res = await fetch(`/${tipo}/${id}`);
  const e = await res.json();

  if (tipo === 'empleados') {
    document.getElementById('idEmpleado').value = e.id;
    document.getElementById('nombre').value = e.nombre;
    document.getElementById('puesto').value = e.puesto;
    document.getElementById('salario').value = e.salario;
    document.getElementById('departamento').value = e.departamento?.id || '';
  } else if (tipo === 'departamentos') {
    document.getElementById('idDep').value = e.id;
    document.getElementById('nombreDep').value = e.nombre;
  } else if (tipo === 'vacacion') {
    document.getElementById('idvacacion').value = e.id;
    document.getElementById('empleadoVac').value = e.empleado?.id || '';
    document.getElementById('inicioVac').value = e.fechaInicio || '';
    document.getElementById('finVac').value = e.fechaFin || '';
  } else if (tipo === 'evaluaciones') {
    document.getElementById('idEval').value = e.id;
    document.getElementById('empleadoEval').value = e.empleado?.id || '';
    document.getElementById('resultadoEval').value = e.resultado || '';
  }
  else if (tipo === 'asistencia') {
  document.getElementById('idAsistencia').value = e.id;
  document.getElementById('empleadoAsis').value = e.empleado?.id || '';
  document.getElementById('fechaAsis').value = e.fecha;
  document.getElementById('entradaAsis').value = e.horaEntrada;
  document.getElementById('salidaAsis').value = e.horaSalida;
  }
  else if (tipo === 'permisos') {
    document.getElementById('idPermiso').value = e.id;
    document.getElementById('empleadoPerm').value = e.empleado?.id || '';
    document.getElementById('motivoPerm').value = e.motivo;
    document.getElementById('inicioPerm').value = e.fechaInicio;
    document.getElementById('finPerm').value = e.fechaFin;
  }
  else if (tipo === 'horasextra') {
    document.getElementById('idHorasExtra').value = e.id;
    document.getElementById('empleadoHex').value = e.empleado?.id || '';
    document.getElementById('fechaHex').value = e.fecha;
    document.getElementById('horasHex').value = e.horas;
    document.getElementById('motivoHex').value = e.motivo;
  }
  else if (tipo === 'viatico') {
    document.getElementById('idViatico').value = e.id;
    document.getElementById('empleadoVia').value = e.empleado?.id || '';
    document.getElementById('fechaVia').value = e.fecha;
    document.getElementById('montoVia').value = e.monto;
    document.getElementById('descVia').value = e.descripcion;
    }

}

// CARGAR SELECTS
async function cargarSelects() {
  try {
    const depRes = await fetch('/departamentos/listar');
    const deps = await depRes.json();
    const depSelect = document.getElementById('departamento');
    if (depSelect) {
      depSelect.innerHTML = '<option value="">Seleccione un Departamento</option>';
      deps.forEach(d => {
        const opt = document.createElement('option');
        opt.value = d.id;
        opt.textContent = d.nombre;
        depSelect.appendChild(opt);
      });
    }

    const empRes = await fetch('/empleados/listar');
    const emps = await empRes.json();
    ['empleadoVac', 'empleadoEval', 'empleadoAsis', 'empleadoPerm','empleadoHex','empleadoVia'].forEach(id => {
      const sel = document.getElementById(id);
      if (sel) {
        sel.innerHTML = '<option value="">Seleccione un Empleado</option>';
        emps.forEach(e => {
          const opt = document.createElement('option');
          opt.value = e.id;
          opt.textContent = e.nombre;
          sel.appendChild(opt);
        });
      }
    });
  } catch (error) {
    console.error('Error en selects', error);
  }
}

// NAVEGACIÓN
window.addEventListener('popstate', () => {
  const path = window.location.pathname;
  const seccion = path.substring(1) || 'empleados';
  const validas = ['empleados', 'departamentos', 'vacacion', 'evaluaciones','asistencia', 'permisos', 'horasextra','viatico'];
  if (validas.includes(seccion)) mostrarSeccion(seccion);
});

// FORMULARIOS
document.getElementById('formEmpleado')?.addEventListener('submit', e => {
  e.preventDefault();
  guardar('empleados', {
    id: document.getElementById('idEmpleado').value || null,
    nombre: document.getElementById('nombre').value,
    puesto: document.getElementById('puesto').value,
    salario: parseFloat(document.getElementById('salario').value),
    departamento: { id: document.getElementById('departamento').value || null }
  }, e.target);
});

document.getElementById('formDepartamento')?.addEventListener('submit', e => {
  e.preventDefault();
  guardar('departamentos', {
    id: document.getElementById('idDep').value || null,
    nombre: document.getElementById('nombreDep').value
  }, e.target);
});

document.getElementById('formvacacion')?.addEventListener('submit', e => {
  e.preventDefault();
  guardar('vacacion', {
    id: document.getElementById('idvacacion').value || null,
    empleado: { id: document.getElementById('empleadoVac').value },
    fechaInicio: document.getElementById('inicioVac').value,
    fechaFin: document.getElementById('finVac').value
  }, e.target);
});

document.getElementById('formEvaluacion')?.addEventListener('submit', e => {
  e.preventDefault();
  guardar('evaluaciones', {
    id: document.getElementById('idEval').value || null,
    empleado: { id: document.getElementById('empleadoEval').value },
    resultado: document.getElementById('resultadoEval').value
  }, e.target);
});

document.getElementById('formAsistencia')?.addEventListener('submit', e => {
  e.preventDefault();
  guardar('asistencia', {
    id: document.getElementById('idAsistencia').value || null,
    empleado: { id: document.getElementById('empleadoAsis').value },
    fecha: document.getElementById('fechaAsis').value,
    horaEntrada: document.getElementById('entradaAsis').value,
    horaSalida: document.getElementById('salidaAsis').value
  }, e.target);
});

document.getElementById('formPermiso')?.addEventListener('submit', e => {
  e.preventDefault();
  guardar('permisos', {
    id: document.getElementById('idPermiso').value || null,
    empleado: { id: document.getElementById('empleadoPerm').value },
    motivo: document.getElementById('motivoPerm').value,
    fechaInicio: document.getElementById('inicioPerm').value,
    fechaFin: document.getElementById('finPerm').value
  }, e.target);
});

document.getElementById('formHorasExtra')?.addEventListener('submit', e => {
  e.preventDefault();
  guardar('horasextra', {
    id: document.getElementById('idHorasExtra').value || null,
    empleado: { id: document.getElementById('empleadoHex').value },
    fecha: document.getElementById('fechaHex').value,
    horas: document.getElementById('horasHex').value,
    motivo: document.getElementById('motivoHex').value
  }, e.target);
});

document.getElementById('formViatico')?.addEventListener('submit', e => {
e.preventDefault();
guardar('viatico', {
id: document.getElementById('idViatico').value || null,
empleado: { id: document.getElementById('empleadoVia').value },
fecha: document.getElementById('fechaVia').value,
monto: parseFloat(document.getElementById('montoVia').value),
descripcion: document.getElementById('descVia').value
}, e.target);
});

// INICIO
window.onload = () => {
  cargarSelects();
  const path = window.location.pathname;
  const seccion = path === '/' ? 'empleados' : path.substring(1);
  const validas = ['empleados', 'departamentos', 'vacacion', 'evaluaciones', 'asistencia','permisos', 'horasextra','viatico'];
  if (validas.includes(seccion)) {
    mostrarSeccion(seccion);
  } else {
    mostrarSeccion('empleados');
    history.replaceState({ seccion: 'empleados' }, '', '/empleados');
  }
};
