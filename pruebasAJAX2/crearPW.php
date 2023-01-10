<?php
//creador contraseñas hash

    $contra = "holahola1";
    $hash = password_hash($contra, PASSWORD_DEFAULT);

    echo $hash;