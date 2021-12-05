README - ProyectoEmpresaClases

Proyecto que crea instancias de Empleados, Jefes, etc... metiendo los datos directamente a clases con esos nombres. (Dichas clases derivan de una superclase llamada Persona).
Este proyecto lo he ido haciendo en la clase: mu divertío; por ahora te coje el DNI y fuera, pero tiene mas cosa bajo la manga:

CLASE Fecha: 

Constructor que le dises el dia, mes y año
Constructor que le dices en texto dia, mes y año también (coje un String, lo transforma a una cadena, y dicha cadena a través de un split se divide mediante el separador "/")

Los dias deben ser del 1 al 28//29(bisiesto)//30//31; depende del mes que se coja ((obv.))
Los meses, del 1 al 12
Los años, deben ser VALIDOS (esto se comprueba con un método estático booleano; validos si son mayores de 1970 y menores del valor del año actual, comprobado con el import
java.util.Date)

y ya los setters y getters
