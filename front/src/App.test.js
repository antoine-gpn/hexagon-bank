import { render, screen, fireEvent } from '@testing-library/react';
import {submitTransaction, updateBalance} from "./utils/api";
import App from './components/App';

jest.mock("./utils/api", () => ({
  submitTransaction: jest.fn(),
  updateBalance: jest.fn(),
}));

describe("App Component", () => {

  test("Correctly displays all subcomponents", () => {
    render(<App />);

    expect(screen.getByRole("banner")).toBeInTheDocument();
    expect(screen.getByText("My balance")).toBeInTheDocument();
    expect(screen.getByText("Last Transactions")).toBeInTheDocument();
    expect(screen.getByRole("contentinfo")).toBeInTheDocument();
  });

  test("call updateBalance on load", () => {
    render(<App />);
    expect(updateBalance).toHaveBeenCalledTimes(1);
  });

  test("Form submission calls submitTransaction", () => {
    render(<App />);

    fireEvent.click(screen.getByText("Make a deposit"));

    const montantInput = screen.getByLabelText("Amount");
    const boutonSoumettre = screen.getByText("Proceed");

    fireEvent.change(montantInput, { target: { value: "100" } });
    fireEvent.click(boutonSoumettre);

    expect(submitTransaction).toHaveBeenCalled();
  });
});
