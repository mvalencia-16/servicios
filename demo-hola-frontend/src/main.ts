import './style.css'

document.querySelector<HTMLDivElement>('#app')!.innerHTML = `
<div id="app-asesor">

  <header class="app-header">
    <span class="header-heart">♡</span>
    <h1 class="header-title">Evaluación de Relación</h1>
  </header>

  <div class="card config-card">
    <input type="hidden" id="id-usuario-input" value="Visita" />
    <div class="config-row" style="align-items:center; gap:8px;">
      <label style="font-size:12px; min-width:fit-content;">Estado conyugal</label>
      <div class="es-casado-opciones" style="gap:5px; flex-wrap:nowrap;">
        <label class="opcion-btn" style="font-size:12px; padding:4px 9px;"><input type="radio" name="es-casado" value="false" checked /> Relación</label>
        <label class="opcion-btn" style="font-size:12px; padding:4px 9px;"><input type="radio" name="es-casado" value="true" /> Casado Religioso</label>
      </div>
    </div>
  </div>

  <div class="card metricas-card">
    <h2 class="card-heading">Indicadores del Amor</h2>
    <div class="indicador-item">
      <div class="metrica-header-row">
        <span class="indicador-nombre">Comunicación</span>
        <span class="indicador-valor">5</span>
      </div>
      <input type="range" class="indicador" min="1" max="10" step="0.5" value="5" />
      <span class="indicador-desc">¿Qué tan fluida y abierta es la comunicación entre ambos?</span>
    </div>
    <div class="indicador-item">
      <div class="metrica-header-row">
        <span class="indicador-nombre">Paciencia</span>
        <span class="indicador-valor">5</span>
      </div>
      <input type="range" class="indicador" min="1" max="10" step="0.5" value="5" />
      <span class="indicador-desc">¿Con qué nivel de calma afrontan los momentos difíciles?</span>
    </div>
    <div class="indicador-item">
      <div class="metrica-header-row">
        <span class="indicador-nombre">Perdón</span>
        <span class="indicador-valor">5</span>
      </div>
      <input type="range" class="indicador" min="1" max="10" step="0.5" value="5" />
      <span class="indicador-desc">¿Qué tan fácil es perdonar y superar conflictos pasados?</span>
    </div>
    <div class="indicador-item">
      <div class="metrica-header-row">
        <span class="indicador-nombre">Independencia</span>
        <span class="indicador-valor">5</span>
      </div>
      <input type="range" class="indicador" min="1" max="10" step="0.5" value="5" />
      <span class="indicador-desc">¿Cada uno conserva su espacio y autonomía personal?</span>
    </div>
    <div class="indicador-item">
      <div class="metrica-header-row">
        <span class="indicador-nombre">Ayuda Mutua</span>
        <span class="indicador-valor">5</span>
      </div>
      <input type="range" class="indicador" min="1" max="10" step="0.5" value="5" />
      <span class="indicador-desc">¿Se apoyan y colaboran activamente el uno con el otro?</span>
    </div>
  </div>

  <div class="card promedio-card" id="promedio-card" hidden>
    <input type="hidden" id="pregunta-input-default" value="Que me aconsejas" />
    <button type="button" class="promedio-circle" id="promedio-btn">
      <span class="promedio-heart-icon">♥</span>
    </button>
    <div class="promedio-valor">Promedio: <strong id="ultima-nota-display">5.0</strong></div>
    <input type="hidden" id="ultima-nota" value="5.0" />
    <div id="respuesta-output-default" class="respuesta-display">La respuesta aparecerá aquí...</div>
  </div>
  
  <div class="card consejero-card card-disabled" id="consejero-card">
    <h2 class="card-heading consejero-heading"><span>🧠</span> Chat Consejeria Espiritual</h2>
        <div class="consejero-pregunta-row">
      <div class="pregunta-input-wrap">
        <input type="text" id="pregunta-input" maxlength="200" placeholder="Escribe tu reflexión o pregunta..." />
        <button type="button" id="limpiar-pregunta-btn" class="limpiar-pregunta-btn" title="Limpiar">&#x2715;</button>
      </div>
      <button id="realizar-pregunta-btn" type="button">Aconsejar</button>
    </div>
    <span id="pregunta-contador" class="pregunta-contador">0 / 200</span>
    <div id="respuesta-output" class="respuesta-display">La respuesta aparecerá aquí...</div>
    <span id="alerta-riesgo" class="alerta-riesgo" hidden></span>
    <button class="guardar-evaluacion-btn" type="button" disabled>📋 Guardar Evaluación</button>
  </div>

  <nav class="bottom-nav">
    <button class="bottom-nav-btn active" id="nav-inicio">
      <span class="bottom-nav-icon-wrap active">
        <svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" viewBox="0 0 24 24" fill="currentColor"><path d="M10 20v-6h4v6h5v-8h3L12 3 2 12h3v8z"/></svg>
      </span>
      <span class="bottom-nav-label">Inicio</span>
    </button>
    <button class="bottom-nav-btn" id="nav-historial">
      <span class="bottom-nav-icon-wrap">
        <svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><circle cx="12" cy="12" r="10"/><polyline points="12 6 12 12 9 15"/><path d="M3.05 11A9 9 0 0 1 12 3"/></svg>
      </span>
      <span class="bottom-nav-label">Historial</span>
    </button>
    <button class="bottom-nav-btn" id="nav-perfil">
      <span class="bottom-nav-icon-wrap">
        <svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><circle cx="12" cy="8" r="4"/><path d="M4 20c0-4 3.6-7 8-7s8 3 8 7"/></svg>
      </span>
      <span class="bottom-nav-label">Perfil</span>
    </button>
  </nav>
`

// Llamadas al API REST del backend demo-hola
// En local: crear demo-hola-frontend/.env con VITE_BACKEND_URL=http://localhost:8080
// En Render: configurar la variable de entorno VITE_BACKEND_URL con la URL del backend
const BACKEND_URL = import.meta.env.VITE_BACKEND_URL ?? 'http://localhost:8088'
const APP_API_KEY = import.meta.env.VITE_APP_API_KEY ?? 'dev-local-key-1234'

async function callSaludo(endpoint: string, spanId: string) {
  const span = document.querySelector<HTMLSpanElement>(`#${spanId}`)!
  try {
    const response = await fetch(`${BACKEND_URL}${endpoint}`)
    if (!response.ok) throw new Error(`HTTP ${response.status}`)
    const text = await response.text()
    span.textContent = text
  } catch (err) {
    span.textContent = `Error: no se pudo conectar al backend (${err})`
  }
}

callSaludo('/saludo1', 'saludo1-result')
callSaludo('/saludo2', 'saludo2-result')

// Llamada al endpoint /realizar-pregunta
const usuarioId = crypto.randomUUID()

async function realizarPregunta() {
  const input = document.querySelector<HTMLInputElement>('#pregunta-input')!
  const output = document.querySelector<HTMLDivElement>('#respuesta-output')!
  const alerta = document.querySelector<HTMLSpanElement>('#alerta-riesgo')!
  const btn = document.querySelector<HTMLButtonElement>('#realizar-pregunta-btn')!
  const idUsuario = (document.querySelector<HTMLInputElement>('#id-usuario-input')?.value.trim()) || 'Visita'
  const esCasado = (document.querySelector<HTMLInputElement>('input[name="es-casado"]:checked')?.value === 'true')
  const ultimaNota = parseFloat(document.querySelector<HTMLInputElement>('#ultima-nota')?.value ?? '5.0')
  const { minimaNota, indicadorMinimaNota } = calcularMinimo()

  const pregunta = input.value.trim()
  if (!pregunta) return

  btn.disabled = true
  output.textContent = 'Consultando...'
  alerta.hidden = true

  try {
    const response = await fetch(`${BACKEND_URL}/api/v1/asesor-espiritual/realizar-pregunta`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'X-Usuario-ID': usuarioId,
        'X-App-Key': APP_API_KEY,
      },
      body: JSON.stringify({ pregunta, idUsuario, esCasado, ultimaNota, minimaNota, indicadorMinimaNota }),
    })
    if (!response.ok) throw new Error(`HTTP ${response.status}`)
    const data = await response.json()
    output.textContent = data.mensajeConsejo
    if (data.alertaRiesgoCritico) {
      alerta.textContent = '⚠️ Alerta: contenido de riesgo crítico detectado'
      alerta.hidden = false
    }
  } catch (err) {
    output.textContent = `Error: no se pudo conectar al backend (${err})`
  } finally {
    btn.disabled = false
  }
}

document.querySelector<HTMLButtonElement>('#realizar-pregunta-btn')!
  .addEventListener('click', realizarPregunta)

document.querySelector<HTMLButtonElement>('#promedio-btn')!
  .addEventListener('click', realizarPreguntaDefault)

async function realizarPreguntaDefault() {
  const preguntaDefault = document.querySelector<HTMLInputElement>('#pregunta-input-default')?.value.trim() || 'Qué me aconsejas'
  const output = document.querySelector<HTMLDivElement>('#respuesta-output-default')!
  const btn = document.querySelector<HTMLButtonElement>('#promedio-btn')!
  const idUsuario = (document.querySelector<HTMLInputElement>('#id-usuario-input')?.value.trim()) || 'Visita'
  const esCasado = (document.querySelector<HTMLInputElement>('input[name="es-casado"]:checked')?.value === 'true')
  const ultimaNota = parseFloat(document.querySelector<HTMLInputElement>('#ultima-nota')?.value ?? '5.0')
  const { minimaNota, indicadorMinimaNota } = calcularMinimo()

  btn.disabled = true
  output.textContent = 'Consultando...'

  try {
    const response = await fetch(`${BACKEND_URL}/api/v1/asesor-espiritual/realizar-pregunta`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'X-Usuario-ID': usuarioId,
        'X-App-Key': APP_API_KEY,
      },
      body: JSON.stringify({ pregunta: preguntaDefault, idUsuario, esCasado, ultimaNota, minimaNota, indicadorMinimaNota }),
    })
    if (!response.ok) throw new Error(`HTTP ${response.status}`)
    const data = await response.json()
    output.textContent = data.mensajeConsejo
    // Mostrar promedio y habilitar chat
    const promedio = document.querySelector<HTMLElement>('#promedio-card')!
    promedio.removeAttribute('hidden')
    const consejero = document.querySelector<HTMLElement>('#consejero-card')!
    consejero.classList.remove('card-disabled')
  } catch (err) {
    output.textContent = `Error: no se pudo conectar al backend (${err})`
  } finally {
    btn.disabled = false
  }
}

document.querySelector<HTMLButtonElement>('#limpiar-pregunta-btn')!
  .addEventListener('click', () => {
    const input = document.querySelector<HTMLInputElement>('#pregunta-input')!
    input.value = ''
    input.focus()
    const contador = document.querySelector<HTMLSpanElement>('#pregunta-contador')!
    contador.textContent = '0 / 200'
    contador.classList.remove('pregunta-contador--limite')
  })

document.querySelector<HTMLInputElement>('#pregunta-input')!
  .addEventListener('keydown', (e) => { if (e.key === 'Enter') realizarPregunta() })

document.querySelector<HTMLInputElement>('#pregunta-input')!
  .addEventListener('input', (e) => {
    const len = (e.target as HTMLInputElement).value.length
    const contador = document.querySelector<HTMLSpanElement>('#pregunta-contador')!
    contador.textContent = `${len} / 200`
    contador.classList.toggle('pregunta-contador--limite', len >= 200)
  })

function updateSliderFill(slider: HTMLInputElement) {
  const min = parseFloat(slider.min)
  const max = parseFloat(slider.max)
  const val = parseFloat(slider.value)
  const pct = ((val - min) / (max - min)) * 100
  slider.style.background = `linear-gradient(to right, #c8445e ${pct}%, #f0d5dc ${pct}%)`
}

function calcularUltimaNota() {
  const sliders = document.querySelectorAll<HTMLInputElement>('.indicador')
  const suma = Array.from(sliders).reduce((acc, s) => acc + parseFloat(s.value), 0)
  const promedio = suma / sliders.length
  const ultimaNota = document.querySelector<HTMLInputElement>('#ultima-nota')!
  ultimaNota.value = promedio.toFixed(1)
  const display = document.querySelector<HTMLElement>('#ultima-nota-display')
  if (display) display.textContent = promedio.toFixed(1)
}

function calcularMinimo(): { minimaNota: number; indicadorMinimaNota: string } {
  const sliders = document.querySelectorAll<HTMLInputElement>('.indicador')
  let minValor = Infinity
  let minNombre = ''
  sliders.forEach((slider) => {
    const valor = parseFloat(slider.value)
    const nombre = slider.closest('.indicador-item')?.querySelector('.indicador-nombre')?.textContent ?? ''
    if (valor < minValor) {
      minValor = valor
      minNombre = nombre
    }
  })
  return { minimaNota: minValor, indicadorMinimaNota: minNombre }
}

document.querySelectorAll<HTMLInputElement>('.indicador').forEach((slider) => {
  updateSliderFill(slider)
  slider.addEventListener('input', () => {
    const valorSpan = slider.closest('.indicador-item')?.querySelector('.indicador-valor') as HTMLSpanElement
    if (valorSpan) valorSpan.textContent = slider.value
    calcularUltimaNota()
    updateSliderFill(slider)
  })
})

document.querySelectorAll<HTMLButtonElement>('.bottom-nav-btn').forEach((btn) => {
  btn.addEventListener('click', () => {
    document.querySelectorAll<HTMLButtonElement>('.bottom-nav-btn').forEach((b) => {
      b.classList.remove('active')
      const wrap = b.querySelector('.bottom-nav-icon-wrap')
      wrap?.classList.remove('active')
    })
    btn.classList.add('active')
    btn.querySelector('.bottom-nav-icon-wrap')?.classList.add('active')
  })
})

document.querySelector<HTMLButtonElement>('#nav-inicio')!
  .addEventListener('click', () => {
    // 1. Ocultar promedio
    document.querySelector<HTMLElement>('#promedio-card')!.setAttribute('hidden', '')
    // 2. Deshabilitar chat
    document.querySelector<HTMLElement>('#consejero-card')!.classList.add('card-disabled')
    // 3. Resetear sliders a 5
    document.querySelectorAll<HTMLInputElement>('.indicador').forEach((slider) => {
      slider.value = '5'
      const valorSpan = slider.closest('.indicador-item')?.querySelector('.indicador-valor') as HTMLSpanElement
      if (valorSpan) valorSpan.textContent = '5'
      updateSliderFill(slider)
    })
    calcularUltimaNota()
    // 4. Seleccionar "Relación"
    const radioRelacion = document.querySelector<HTMLInputElement>('input[name="es-casado"][value="false"]')
    if (radioRelacion) radioRelacion.checked = true
    // 5. Limpiar respuestas
    const outputDefault = document.querySelector<HTMLDivElement>('#respuesta-output-default')
    if (outputDefault) outputDefault.textContent = 'La respuesta aparecerá aquí...'
    const outputChat = document.querySelector<HTMLDivElement>('#respuesta-output')
    if (outputChat) outputChat.textContent = 'La respuesta aparecerá aquí...'
    const inputChat = document.querySelector<HTMLInputElement>('#pregunta-input')
    if (inputChat) inputChat.value = ''
    const contador = document.querySelector<HTMLSpanElement>('#pregunta-contador')
    if (contador) contador.textContent = '0 / 200'
    const alerta = document.querySelector<HTMLSpanElement>('#alerta-riesgo')
    if (alerta) { alerta.hidden = true; alerta.textContent = '' }
  })

