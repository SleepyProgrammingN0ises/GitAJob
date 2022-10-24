<?php
    if (isset($_REQUEST['elec'])) {
        echo "<b>" . $_REQUEST['elec'] . "</b>" . " seleccSionado";
    } else {
        echo "No has introducido ninguna opción";   //Imposible pero weno
    }