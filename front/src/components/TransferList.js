import { useEffect } from "react";
import "../styles/TransferList.css";
import Transfer from "./Transfer";

function TransferList({ transferList, updateTransferList }) {
    const baseUrl = "http://localhost:8080/";

    useEffect(() => {
        fetch(baseUrl + "transactions")
            .then((response) => response.json())
            .then((response) => updateTransferList(response));
    }, []);

    return (
        <div id="history">
            <h2>Last Transactions</h2>
            {transferList.map((transfer, index) => (
                <Transfer key={index} transfer={transfer} />
            ))}
        </div>
    );
}

export default TransferList;
