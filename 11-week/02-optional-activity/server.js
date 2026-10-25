const express = require("express");
const jwt = require("jsonwebtoken");
const bcrypt = require("bcryptjs");

const app = express();
app.use(express.json());

// En producción esto va en una variable de entorno (.env), nunca en el código.
const JWT_SECRET = process.env.JWT_SECRET || "cambia-este-secreto";
const JWT_EXPIRES_IN = "1h";
const PORT = process.env.PORT || 3000;

// "Base de datos" en memoria para la práctica.
const users = [
  {
    id: 1,
    username: "admin",
    passwordHash: bcrypt.hashSync("1234", 10),
  },
];

// ---------- Middleware de autenticación ----------
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

// ---------- Rutas públicas ----------
app.get("/", (req, res) => {
  res.json({ message: "API funcionando. Usa POST /login para obtener un token." });
});

app.post("/login", (req, res) => {
  const { username, password } = req.body || {};
  const user = users.find((u) => u.username === username);

  if (!user || !bcrypt.compareSync(password || "", user.passwordHash)) {
    return res.status(401).json({ error: "Credenciales incorrectas" });
  }

  const token = jwt.sign(
    { sub: user.id, username: user.username },
    JWT_SECRET,
    { expiresIn: JWT_EXPIRES_IN }
  );

  res.json({ token });
});

// ---------- Rutas protegidas ----------
app.get("/perfil", authRequired, (req, res) => {
  res.json({ id: req.user.sub, username: req.user.username });
});

app.get("/datos-secretos", authRequired, (req, res) => {
  res.json({ message: `Hola ${req.user.username}, este contenido está protegido.` });
});

app.listen(PORT, () => {
  console.log(`Servidor en http://localhost:${PORT}`);
});
