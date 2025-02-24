import "../styles/Transfer.css";
import deposit from "../assets/deposit.png";
import withdraw from "../assets/withdraw.png";

function Transfer({ transfer }) {
    return (
        <div className="transfer">
            <img
                className="img"
                src={transfer.operator === "+" ? deposit : withdraw}
            />

            <div className="transfer-text">
                <div className="motif">{transfer.label}</div>
                <div className="transfer-date">
                    {new Date(transfer.date).toLocaleString("fr-FR", {
                        day: "2-digit",
                        month: "2-digit",
                        year: "numeric",
                        hour: "2-digit",
                        minute: "2-digit",
                    })}
                </div>
            </div>

            <div
                className={`${
                    transfer.operator === "+" ? "deposit" : "withdraw"
                } amount`}
            >
                {transfer.operator} {transfer.amount}€
            </div>
        </div>
    );
}

export default Transfer;
