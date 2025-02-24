import "../styles/Form.css";
import { useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCaretUp, faCaretDown } from "@fortawesome/free-solid-svg-icons";

function Form({ submitTransaction, setToggleForm, toggleForm }) {
    const [formType, setFormType] = useState("");
    const [value, setValue] = useState(0);
    const [label, setLabel] = useState("");

    return (
        <div id="actions">
            <div id="btns-bloc">
                <button
                    id="deposit"
                    onClick={() => {
                        setToggleForm(true);
                        setFormType("+");
                    }}
                >
                    <FontAwesomeIcon icon={faCaretUp} /> Make a deposit
                </button>
                <button
                    id="withdraw"
                    onClick={() => {
                        setToggleForm(true);
                        setFormType("-");
                    }}
                >
                    <FontAwesomeIcon icon={faCaretDown} /> Make a withdraw
                </button>
            </div>

            {toggleForm && (
                <form>
                    <fieldset>
                        <legend>Reason</legend>
                        <input aria-label="Reason"
                            type="text"
                            value={label}
                            onChange={(e) => setLabel(e.target.value)}
                        />
                    </fieldset>

                    <fieldset>
                        <legend>Amount</legend>
                        <input aria-label="Amount"
                            type="number"
                            value={value}
                            onChange={(e) => setValue(e.target.value)}
                        />
                    </fieldset>
                    <div id="end-btns">
                        <button
                            id="submit"
                            onClick={(e) => {
                                e.preventDefault();
                                submitTransaction(
                                    value,
                                    formType,
                                    label,
                                    new Date().toISOString()
                                );
                            }}
                        >
                            Proceed
                        </button>
                        <button id="submit" onClick={() => setToggleForm(false)}>
                            Cancel
                        </button>
                    </div>
                </form>
            )}
        </div>
    );
}

export default Form;
