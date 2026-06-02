document.addEventListener("DOMContentLoaded", () => {
    document.querySelectorAll('.fondo-examen').forEach(img => {

        let numero = Math.floor(Math.random() * 8) + 1;
        img.src = `/image/fondos-examenes/fondo-${numero}.jpg`;

    });
});
