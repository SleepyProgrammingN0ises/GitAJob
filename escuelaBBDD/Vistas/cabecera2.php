<!--MUESTRA EL EMAIL DEL USUARIO
    USAR EN PHP:
    $_SESSION['email'];
    Enlaces al gusto
    -->
<!-- EL USUARIO PROFE Ó ALUMNO TENDRÁ MENOS ACCESO QUE EL ADMIN -->
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">
    <div class="container">
        <a class="navbar-brand" href="#">
            <img src="./Vistas/imgs/logoalfonsoxi.jpeg" alt="..." height="52">
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto">
                <li class="white">
                    <p class="peq" style="color: darkgoldenrod"><b>Bienvenida/o, <?php echo $_SESSION['email'] . ", <a href='logout.php'>CERRAR SESIÓN</a>"; ?></b></p>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="../index.php">Inicio</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        OPCIONES
                    </a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="#">Recargar</a></li>
                        <li>
                            <hr class="dropdown-divider">
                        </li>
                        <li><a class="dropdown-item" href="logout.php">Cerrar Sesión</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>