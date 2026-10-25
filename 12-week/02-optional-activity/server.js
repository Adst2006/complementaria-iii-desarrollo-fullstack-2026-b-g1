const express = require("express");
const cors = require("cors");
const jwt = require("jsonwebtoken");
const bcrypt = require("bcryptjs");

const app = express();

// ---------- CORS ----------
// El frontend corre en otro origen (otro puerto o file://), así que el navegador
// bloquea las peticiones a menos que el backend las permita explícitamente.
// Para la práctica se permite cualquier origen; en producción se limita a tu dominio:
//   cors({ origin: "https://mi-frontend.com" })
app.use(
  cors({
    origin: "*",
    methods: ["GET", "POST", "OPTIONS"],
    allowedHeaders: ["Content-Type", "Authorization"],
  })
);

app.use(express.json());

const JWT_SECRET = process.env.JWT_SECRET || "cambia-este-secreto";
const JWT_EXPIRES_IN = "1h";
const PORT = process.env.PORT || 3000;

// Retraso artificial (ms) para poder ver el estado "cargando" en el frontend.
const DELAY_MS = Number(process.env.DELAY_MS ?? 600);
const delay = (req, res, next) => setTimeout(next, DELAY_MS);

const users = [
  {
    id: 1,
    username: "admin",
    passwordHash: bcrypt.hashSync("1234", 10),
  },
];

function authRequired(req, res, next) {
  const header = req.headers["authorization"] || "";
  const [scheme, token] = header.split(" ");

  if (scheme !== "Bearer" || !token) {
    return res.status(401).json({ error: "Token requerido" });
  }

  try {
    req.user = jwt.verify(token, JWT_SECRET);
    next();
  } catch (err) {
    return res.status(401).json({ error: "Token inválido o expirado" });
  }
}

app.get("/", (req, res) => {
  res.json({ message: "API funcionando. Usa POST /login para obtener un token." });
});

app.post("/login", delay, (req, res) => {
  const { username, password } = req.body || {};
  const user = users.find((u) => u.username === username);

  if (!user || !bcrypt.compareSync(password || "", user.passwordHash)) {
    return res.status(401).json({ error: "Usuario o contraseña incorrectos" });
  }

  const token = jwt.sign(
    { sub: user.id, username: user.username },
    JWT_SECRET,
    { expiresIn: JWT_EXPIRES_IN }
  );

  res.json({ token });
});

app.get("/perfil", delay, authRequired, (req, res) => {
  res.json({ id: req.user.sub, username: req.user.username });
});

app.get("/datos-secretos", delay, authRequired, (req, res) => {
  res.json({
    message: `Hola ${req.user.username}, este contenido está protegido.`,
    items: [
      { id: 1, titulo: "Informe de notas", estado: "publicado" },
      { id: 2, titulo: "Horario del semestre", estado: "borrador" },
      { id: 3, titulo: "Lista de proyectos", estado: "publicado" },
    ],
  });
});

app.listen(PORT, () => {
  console.log(`API en http://localhost:${PORT}`);
});
