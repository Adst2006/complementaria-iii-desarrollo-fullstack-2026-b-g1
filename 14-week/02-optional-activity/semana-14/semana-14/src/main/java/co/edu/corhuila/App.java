package co.edu.corhuila;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

/**
 * Backend minimo en Java (sin frameworks ni dependencias externas)
 * para la actividad de Docker - Semana 14.
 *
 * Endpoints:
 *   GET /        -> mensaje de bienvenida (JSON)
 *   GET /health  -> estado del servicio (JSON)
 */
public class App {

    public static void main(String[] args) throws IOException {
        int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8080"));

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        server.createContext("/health", exchange ->
                responder(exchange, "{\"status\":\"ok\"}"));

        server.createContext("/", exchange ->
                responder(exchange,
                        "{\"mensaje\":\"Hola desde un contenedor Docker\","
                                + "\"semana\":14,"
                                + "\"asignatura\":\"Desarrollo Fullstack\"}"));

        server.start();
        System.out.println("Servidor escuchando en el puerto " + port);
    }

    private static void responder(HttpExchange exchange, String json) throws IOException {
        byte[] body = json.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=utf-8");
        exchange.sendResponseHeaders(200, body.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(body);
        }
    }
}
