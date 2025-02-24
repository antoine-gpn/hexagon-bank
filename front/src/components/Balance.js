import "../styles/Balance.css";
import { useEffect } from "react";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faLocationDot} from "@fortawesome/free-solid-svg-icons";

function Balance({ balance, updateBalance }) {
    useEffect(() => {
        updateBalance();
    }, []);

    return (
        <div>
        <div id="user">
            <h2>
                Antoine GRAPPIN
                <br/>
                <span id="user-small">
                N° 1234567890B
                <br/>
                <FontAwesomeIcon icon={faLocationDot}/> Lille Agency
              </span>
            </h2>
        </div>
    <div id="balance">
        <h3>My balance</h3>
        <h2>
            {balance && balance.toFixed(2)}€ <span id="currency">EUR</span>
        </h2>
    </div>
    </div>
)
    ;
}

export default Balance;
