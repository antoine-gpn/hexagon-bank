const baseUrl = "http://localhost:8080/";

export function submitTransaction(amount, operator, label, date, setToggleForm, updateTransferList, updateBalance, transferList) {
    fetch(baseUrl + "transaction", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            amount: amount,
            operator: operator,
            label: label,
            date: date,
        }),
    })
        .then(async (response) => {
            if (!response.ok) {
                const errorData = await response.json();
                throw new Error(errorData.message || "An error has occurred");
            }
            return response.json();
        })
        .then((response) => {
            setToggleForm(false);
            updateTransferList([response, ...transferList]);
            updateBalance();
        })
        .catch((error) => {
            alert(error.message);
        });
}

export function updateBalance(setBalance) {
    fetch(baseUrl + "balance")
        .then((response) => response.json())
        .then((response) => setBalance(response))
        .catch((error) => console.error("Error retrieving balance :", error));
}
