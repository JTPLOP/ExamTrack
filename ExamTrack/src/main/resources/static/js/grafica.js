// grafica.js - Gráfico de evolución de aprobados y suspensos por mes
// Los datos se obtienen del endpoint /api/grafica/datos

document.addEventListener("DOMContentLoaded", function () {

    var meses = ["Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"];

    fetch("/api/grafica/datos")
        .then(function (response) {
            return response.json();
        })
        .then(function (datos) {

            var ctx = document.getElementById("graficaEvolucion").getContext("2d");

            new Chart(ctx, {
                type: "line",
                data: {
                    labels: meses,
                    datasets: [
                        {
                            label: "% Aprobados",
                            data: datos.aprobados,
                            borderColor: "green",
                            backgroundColor: "rgba(0, 128, 0, 0.1)",
                            fill: false,
                            tension: 0.3
                        },
                        {
                            label: "% Suspensos",
                            data: datos.suspensos,
                            borderColor: "red",
                            backgroundColor: "rgba(255, 0, 0, 0.1)",
                            fill: false,
                            tension: 0.3
                        }
                    ]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: false,
                    plugins: {
                        legend: {
                            position: "top"
                        }
                    },
                    scales: {
                        y: {
                            beginAtZero: true,
                            max: 100,
                            ticks: {
                                callback: function (value) {
                                    return value + "%";
                                }
                            }
                        }
                    }
                }
            });

        });

});
