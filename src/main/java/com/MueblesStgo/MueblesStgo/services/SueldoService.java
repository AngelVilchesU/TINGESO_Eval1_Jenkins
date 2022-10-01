package com.MueblesStgo.MueblesStgo.services;

import com.MueblesStgo.MueblesStgo.entities.*;
import com.MueblesStgo.MueblesStgo.repositories.SueldoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.*;

@Service
public class SueldoService {
    @Autowired // Proporciona control de instancias
    SueldoRepository sueldoRepository;

    @Autowired // Proporciona control de instancias
    EmpleadoService empleadoService;

    @Autowired // Proporciona control de instancias
    CategoriaService categoriaService;

    @Autowired // Proporciona control de instancias
    BonificacionService bonificacionService;

    @Autowired // Proporciona control de instancias
    DescuentoService descuentoService;

    @Autowired // Proporciona control de instancias
    AutorizacionService autorizacionService;

    @Autowired // Proporciona control de instancias
    JustificativoService justificativoService;

    //private String nombreArchivo = "DATA.txt"; // constante nombre del archivo recibido
    //private String carpetaDestino="Marcas//"; // constante nombre de la carpeta contenedora de dicho archivo
    private ArrayList<ArchivoEntity> archivoEntityArrayList = new ArrayList<>(); // Arreglo por concepto de lectura de contenido
    private int diasDelMes;
    private int mesEvaluado;
    private int anioEvaluado;

    /*
    El siguiente método retorna un arreglo el cual contiene TODOS los sueldos de la base de datos
     */
    public ArrayList<SueldoEntity> obtenerSueldo(){
        return (ArrayList<SueldoEntity>) sueldoRepository.findAll();
    }

    /*
    El siguiente método permite guardar un sueldo en la base de datos
     */
    public SueldoEntity guardarSueldo(SueldoEntity sueldo){
        return sueldoRepository.save(sueldo);
    }

    /*
    El siguiente método permite determinar si, dado una fecha, el dia considerado corresponde a un
    dia de semana (laboral) o no (fin de semana)
     */
    public boolean esDiaDeSemana(int dia, int mes, int anio){
        DayOfWeek diaSemana = LocalDate.of(anio, mes, dia).getDayOfWeek();
        new Locale("es", "ES");
        String diaSemanaStr = diaSemana.getDisplayName(TextStyle.FULL, Locale.forLanguageTag("es-ES"));
        if (diaSemanaStr.equals("sábado") || diaSemanaStr.equals("domingo")){
            return false;
        }
        return true;
    }

    /*
    El siguiente método permite determinar si un año posee 365 días o 366 días (bisiesto cada cuatro años)
    de esta forma, los días del mes a excepción de febrero se mantienen constantes. Sin embargo, si el
    año "actual" es bisiesto, febrero tendrá 29 días, caso contrario serán 28 días
    */
    public boolean esBisiesto(float anio){
        anio = anio / 4;
        if ((anio % 1) == 0){
            return true;
        }
        return false;
    }

    /*
    El siguiente método considera la obtención de los días de un mes en consideración con si el
    año ingresado es bisiesto o no
    */
    public int diasDelMes(int mes, int anio){
        if ((mes == 1) || (mes == 3) || (mes == 5) || (mes == 7) || (mes == 8) || (mes == 10) || (mes == 12)){
            return 31;
        }
        else if (mes == 2){
            if (esBisiesto(anio)){
                return 29;
            }
            else {
                return 28;
            }
        }
        else {
            return 30;
        }
    }

    /*
    El siguiente método
     */
    public String calculoPlanillas(String ruta, String nombreArchivo){
        File archivo = new File(ruta + nombreArchivo);
        try (Scanner escaner = new Scanner(archivo)){
            /*
            Se opta por una solución que evalue el contenido de la carpeta contenedora de las marcas en lugar
            de extraer la información de la BD puesto que se contempla que esta ultima posea información
            de marcas anteriores al mes evaluado lo cual pueda interferir en los calculos de planillas realizados
             */
            while (escaner.hasNextLine()){ // Mientras el archivo posea una siguiente linea (no se lea completamente)
                String linea = escaner.nextLine(); // Se extrae la linea "actual"
                String[] parte = linea.split(";");  // el string se divide en partes a partir del caracter ";"
                String fechaTmp = parte[0].replace("/", "-"); // La primera de estas partes contempla la fecha
                LocalDate fecha = LocalDate.parse(fechaTmp); // El string fecha es convertido a LocalDate
                String horaTmp = parte[1]; // La segunda parte contempla la hora
                LocalTime hora = LocalTime.parse(horaTmp); // El string hora es convertido a LocalTime
                String rut = parte[2]; // La tercera parte contempla el rut
                diasDelMes = diasDelMes(fecha.getMonthValue(), fecha.getYear());
                mesEvaluado = fecha.getMonthValue();
                anioEvaluado = fecha.getYear();
                EmpleadoEntity empleado = new EmpleadoEntity();
                ArchivoEntity marca = new ArchivoEntity(fecha, hora, rut, empleado);
                archivoEntityArrayList.add(marca); // Cada marca a considerar se almacena en el arreglo
            }
            ArrayList<EmpleadoEntity> empleados = empleadoService.obtenerEmpleados(); // Se obtienen todos los empleados
            for (int j = 0; j < empleados.size(); j++){ // Mientras no se haya evaluado a cada usuario
                String rutEmpleado = empleados.get(j).getRut(); // Se extrae el rut del empleado "actual"
                String nombreApellido = empleados.get(j).getNombre().concat(" " + empleados.get(j).getApellido()); // Se extrae y concatena nombre y apellido del empleado "actual"
                char categoria = empleados.get(j).getCategoria().getCategoria(); // Se extrae la categoria del empleado "actual"
                float anioIngreso = empleados.get(j).getFechaIngresoEmpresa().getYear();
                float aniosServicio = anioEvaluado - anioIngreso; // Se extrae el año de ingreso a la empresa calculando los años trabajados
                float sueldoFijoMensual = empleados.get(j).getCategoria().getSueldoFijoMensual(); // Se extrae el sueldo fijo mensual de acuerdo con la categoria
                float montoBonificacionAniosServicio = bonificacionService.bonificacionAniosServicio(aniosServicio); // Se extrae la bonificación de años de servicio
                float sueldoBruto = sueldoFijoMensual; // Se define tempranamente el sueldo bruto (sin considerar beneficios aún)
                float cotizacionPrevisional = descuentoService.obtenerCotizaciones()[0]; // Se extrar el porcentaje de descuento figurado por la cotización previsional
                float cotizacionSalud = descuentoService.obtenerCotizaciones()[1]; // Se extrar el porcentaje de descuento figurado por la cotización salud
                float montoSueldoFinal = 0; // Se define tempranamente el monto del sueldo final
                float descuentos = 0; // Porcentaje de descuento inicializado en 0
                float horasExtra = 0; // Horas extras inicializadas en 0
                float pagoHorasExtra = 0; // Pago por concepto de horas extra inicializado en 0
                float pagoAniosServicio = 0; // Pago por años de servicio inicializado en 0
                int i = 1;
                for (i = 1; i <= diasDelMes; i++){ // Mientras no se evaluen los dias respectivos del mes considerado
                    if(esDiaDeSemana(i, mesEvaluado, anioEvaluado)){ // Si la fecha a evaluar responde a un día de semana (laboral)
                        LocalDate fechaEvaluada = LocalDate.of(anioEvaluado, mesEvaluado, i);
                        LocalTime horaInicio = archivoEntityArrayList.get(0).getHoraIngresoSalida(); // Se inicializa la variable
                        LocalTime horaSalida;
                        int finTurno = 0; // su maximo es 2, la entrada (1) y salida (2)
                        for (int k = 0; k < archivoEntityArrayList.size(); k++){ // Mientras no se evaluen todas las marcas de reloj
                            if(archivoEntityArrayList.get(k).getRutEmpleado().equals(empleados.get(j).getRut())
                            && archivoEntityArrayList.get(k).getFecha().equals(fechaEvaluada)){ // Si el rut y fecha evaluadas responde a la marca de reloj "actual"
                                finTurno = finTurno + 1; //
                                if(finTurno == 1) { // Si responde a horario de entrada
                                    horaInicio = archivoEntityArrayList.get(k).getHoraIngresoSalida(); // Se extrae la primera hora de la marca de acuerdo al rut y fecha "actual"
                                }
                                else if (finTurno == 2){ // Si responde a horario de salida/se completa el horario
                                    horaSalida = archivoEntityArrayList.get(k).getHoraIngresoSalida(); // Se extrae la segunda hora de la marca de acuerdo al rut y fecha "actual"
                                    horaSalida = horaSalida.minusHours(horaInicio.getHour()); // Se resta la hora de salida con la de entrada (solo horas)
                                    horaSalida= horaSalida.minusMinutes(horaInicio.getMinute()); // Se resta la hora de salida con la de entrada (solo minutos)
                                    LocalTime tiempoFaltanteTrabajo = descuentoService.tiempoNoTrabajo(horaSalida); // Se define el tiempo (horas) de trabajo faltante
                                    // Referente a descuentos
                                    if(descuentoService.descuento(tiempoFaltanteTrabajo).get(1) != 1.0){ // No puede justificar su ausencia
                                        descuentos = descuentos + descuentoService.descuento(tiempoFaltanteTrabajo).get(0); // Figura un descuento
                                    }
                                    else { // Puede justificar su ausencia
                                        if(!justificativoService.estaJustificado(fechaEvaluada, rutEmpleado)){ // No está justificado
                                            descuentos = descuentos + descuentoService.descuento(tiempoFaltanteTrabajo).get(0); // Figura un descuento
                                        }
                                    }
                                    // Referente a bonificaciones por concepto de horas extras
                                    if(autorizacionService.tieneAutorizacion(fechaEvaluada, rutEmpleado)){ // Posee autorización asociada a fecha y rut
                                        horasExtra = horasExtra + autorizacionService.horasExtra(fechaEvaluada, rutEmpleado); // Se suman horas extras
                                    }
                                }
                            }
                        }
                    }

                }
                pagoHorasExtra = pagoHorasExtra + categoriaService.pagoHorasExtra(horasExtra, categoria); // Se calcula el pago de horas extra de acuerdo a las horas y categoria
                pagoAniosServicio = bonificacionService.sueldoBonificacionPorcentual(sueldoFijoMensual, montoBonificacionAniosServicio); // Se adiciona la bonificación por años de servicio
                sueldoBruto = descuentoService.aplicacionDescuentos(sueldoBruto, descuentos); // Se calcula el sueldo bruto
                sueldoBruto = sueldoBruto + pagoAniosServicio + pagoHorasExtra; // Se calcula el sueldo bruto
                //descuentos = descuentos + cotizacionPrevisional + cotizacionSalud; // Se adicionan al porcentaje de descuento las cotizaciones
                montoSueldoFinal = descuentoService.aplicacionDescuentos(sueldoBruto, (cotizacionPrevisional + cotizacionSalud)); // Se calcula el sueldo final
                LocalDate fecha = LocalDate.of(anioEvaluado, mesEvaluado, diasDelMes(mesEvaluado, anioEvaluado));
                SueldoEntity sueldo = new SueldoEntity(rutEmpleado, nombreApellido, categoria, aniosServicio, sueldoFijoMensual, pagoAniosServicio, pagoHorasExtra, descuentos, sueldoBruto, cotizacionPrevisional, cotizacionSalud, montoSueldoFinal, fecha);
                guardarSueldo(sueldo); // Se guarda el sueldo calculado en la base de datos
            }
        }
        catch (FileNotFoundException error){
            error.printStackTrace();
        }
        return "El calculo se ha realizado existosamente";
    }

    /*
    El siguiente método permite mostrar por pantalla un reporte de la planilla de sueldos con los
    siguientes datos: rut, nombre empleado (apellidos + nombres), categoria, años de servicio empresa
    sueldo fijo mensual, monto bonificación años servicio, monto pago horas extra, monto descuentos,
    sueldo bruto, cotización previsional, cotización salud y monto sueldo final
     */
    public String mostrarSueldos(int mes, int anio){
        ArrayList<SueldoEntity> sueldoEntityArrayList = obtenerSueldo();
        for (int i = 0; i < sueldoEntityArrayList.size(); i++){
            if (sueldoEntityArrayList.get(i).getFecha().getYear() == anio &&
            sueldoEntityArrayList.get(i).getFecha().getMonthValue() == mes){
                System.out.println(sueldoEntityArrayList.get(i).getRutEmpleado());
            }
        }
        return "Reporte generado exitosamente";
    }
}
