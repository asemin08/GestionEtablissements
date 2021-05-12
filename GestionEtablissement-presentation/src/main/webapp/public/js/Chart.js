// Bar chart
var pathsite = document.location.origin;
let url = pathsite +'/GestionEtablissement_presentation_war/StatistiqueValue';


new Chart(document.getElementById("barChartMoyenne"), {
    type: 'bar',
    data: {
        labels: ["Africa", "Asia", "Europe", "Latin America", "North America"],
        datasets: [
            {
                label: "Classement des étudiants par moyenne",
                backgroundColor: ["#3e95cd", "#8e5ea2","#3cba9f","#e8c3b9","#c45850"],
                data: [2478,5267,734,784,433]
            }
        ]
    },
    options: {
        title: {
            display: true,
            text: 'Classement des étudiants par moyenne'
        }
    }
});

new Chart(document.getElementById("pieCharMoyenne"), {
        type: 'pie',
        data: {
            labels: ["Pending", "InProgress", "OnHold", "Complete", "Cancelled"],
            datasets: [
                {
                    data: [21,39, 10, 14,16],
                    backgroundColor: [
                        "#FF6384",
                        "#4BC0C0",
                        "#FFCE56",
                        "#E7E9ED",
                        "#36A2EB"
                    ]
                }]
        },
        options: {
            title: {
                display: true,
                text: 'Pie Chart'
            },
            responsive: true,
            maintainAspectRatio: false,
        }
});
