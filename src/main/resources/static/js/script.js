document.getElementById("fetchButton").addEventListener("click", fetchData);

function fetchData() {
    const foodType = document.getElementById("foodTypeInput").value;

    if (foodType === "") {
        alert("Please select a valid food type.");
        return;
    }

    fetch(`http://10.11.1.11:8080/getFoodMenu?foodType=${encodeURIComponent(foodType)}`)
        .then(response => response.json())
        .then(data => {
            console.log(data); // Log the data to the console
            const tableBody = document.getElementById("menuTableBody");
            displayData(data.foodList, tableBody);
        })
        .catch(error => console.error("Error fetching data:", error));
}

function displayData(foodList, tableBody) {
    tableBody.innerHTML = ""; // Clear previous data

    if (foodList && Array.isArray(foodList)) {
        foodList.forEach(item => {
            const newRow = document.createElement("tr");
            newRow.innerHTML = `
                <td>${item.date}</td>
                <td>${item.day}</td>
                <td>${item.mainDish}</td>
                <td>${item.sideDish}</td>
                <td>${item.sweet}</td>
                <td>${item.coldDrink}</td>
                <td>${item.fruit}</td>
                <td>${item.specialDays}</td>
                <td>${item.regularSalad}</td>
            `;
            tableBody.appendChild(newRow);
        });
    } else {
        tableBody.innerHTML = `<tr><td colspan="9">No data available.</td></tr>`;
    }
}



document.getElementById("fetchButton1").addEventListener("click", fetchDataByDate);

function fetchDataByDate() {
    const foodType = document.getElementById("foodTypeInput1").value;
    const date = document.getElementById("dateInput").value;

    if (foodType === "" || date === "") {
        alert("Please select a valid food type and date.");
        return;
    }

    fetch(`http://10.11.1.11:8080/searchMenuByDate?foodType=${encodeURIComponent(foodType)}&date=${encodeURIComponent(date)}`)
        .then(response => response.json())
        .then(data => {
            console.log(data); // Log the data to the console
            const tableBody = document.getElementById("menuTableBody");
            displayData(data.foodList, tableBody);

            // Show or hide the table based on data availability
            const table = document.getElementById("menuTable");
            if (data.foodList && data.foodList.length > 0) {
                table.style.display = "table";
            } else {
                table.style.display = "none";
            }
        })
        .catch(error => console.error("Error fetching data:", error));
}

function displayData(foodList, tableBody) {
    tableBody.innerHTML = ""; // Clear previous data

    if (foodList && Array.isArray(foodList)) {
        foodList.forEach(item => {
            const newRow = document.createElement("tr");
            newRow.innerHTML = `
                <td>${item.date}</td>
                <td>${item.day}</td>
                <td>${item.mainDish}</td>
                <td>${item.sideDish}</td>
                <td>${item.sweet}</td>
                <td>${item.coldDrink}</td>
                <td>${item.fruit}</td>
                <td>${item.specialDays}</td>
                <td>${item.regularSalad}</td>
            `;
            tableBody.appendChild(newRow);
        });
    } else {
        tableBody.innerHTML = `<tr><td colspan="9">No data available.</td></tr>`;
    }
}
