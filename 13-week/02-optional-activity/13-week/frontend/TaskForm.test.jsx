// @vitest-environment jsdom
import { render, screen, cleanup } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import { describe, it, expect, vi, afterEach } from "vitest";
import "@testing-library/jest-dom/vitest";
import TaskForm from "./TaskForm.jsx";

afterEach(cleanup);

describe("Frontend - componente TaskForm", () => {
  it("envía el título escrito y limpia el campo (camino feliz)", async () => {
    const onAdd = vi.fn();
    render(<TaskForm onAdd={onAdd} />);

    const input = screen.getByLabelText("Nueva tarea");
    await userEvent.type(input, "Estudiar para el parcial");
    await userEvent.click(screen.getByRole("button", { name: "Agregar" }));

    expect(onAdd).toHaveBeenCalledTimes(1);
    expect(onAdd).toHaveBeenCalledWith("Estudiar para el parcial");
    expect(input).toHaveValue("");
  });

  it("CASO ERROR: muestra mensaje y NO llama onAdd si el título está vacío", async () => {
    const onAdd = vi.fn();
    render(<TaskForm onAdd={onAdd} />);

    await userEvent.click(screen.getByRole("button", { name: "Agregar" }));

    expect(screen.getByRole("alert")).toHaveTextContent("El título es obligatorio");
    expect(onAdd).not.toHaveBeenCalled();
  });

  it("CASO LÍMITE: un título de solo espacios se considera vacío", async () => {
    const onAdd = vi.fn();
    render(<TaskForm onAdd={onAdd} />);

    await userEvent.type(screen.getByLabelText("Nueva tarea"), "     ");
    await userEvent.click(screen.getByRole("button", { name: "Agregar" }));

    expect(screen.getByRole("alert")).toBeInTheDocument();
    expect(onAdd).not.toHaveBeenCalled();
  });
});
