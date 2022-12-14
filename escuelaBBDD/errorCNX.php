<style>
    body {
        background-color: #FCC22F;
        display: flex;
        justify-content: center;
        flex-flow: column nowrap;
        text-align: center;
    }
    h2 {
        font-size: 48px;
        font-family: Bahnschrift, "DejaVu Serif", "JetBrains Mono ExtraBold", sans-serif;
    }
    p {
        font-size: 24px;
        font-family: Calibri, KacstBook, "Liberation Sans Narrow", sans-serif;
    }
    img {
        text-align: center;
    }
    .error100 {
        color: black;
        align-self: center;
    }
</style>
<?php
    echo "<div class='error100'>";
    echo "<img src='./Vistas/imgs/enchufe-electrico-enchufe-concepto-error-404-simbolo-desconexion-conexion_574175-568.png' alt='imgErrorBD'/>";
    echo "<h2>ERROR AL CONECTAR A LA BBDD</h2>";
    echo "<p>Por favor, chequée su conexión, y <a href='index.php'>vuelva a intentarlo!</a></p>";
    echo "</div>";