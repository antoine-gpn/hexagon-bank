import "../styles/App.css";
import Header from "./Header";
import Balance from "./Balance";
import History from "./TransferList";
import Form from "./Form";
import Footer from "./Footer";
import { useState } from "react";
import { submitTransaction, updateBalance } from "../utils/api";

function App() {
  const [toggleForm, setToggleForm] = useState(false);
  const [transferList, updateTransferList] = useState([]);
  const [balance, setBalance] = useState();

  return (
      <div className="App">
        <Header />
        <div id="body">
          <div id="left-bloc">
            <Balance balance={balance} updateBalance={() => updateBalance(setBalance)} />
            <Form
                submitTransaction={(amount, operator, label, date) =>
                    submitTransaction(
                        amount,
                        operator,
                        label,
                        date,
                        setToggleForm,
                        updateTransferList,
                        () => updateBalance(setBalance),
                        transferList
                    )
                }
                setToggleForm={setToggleForm}
                toggleForm={toggleForm}
            />
          </div>
          <History
              transferList={transferList}
              updateTransferList={updateTransferList}
          />
        </div>
        <Footer/>
      </div>
  );
}

export default App;
