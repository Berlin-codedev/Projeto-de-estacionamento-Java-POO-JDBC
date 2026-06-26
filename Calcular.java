package ServiceLayer;

import java.time.Duration;
import java.time.LocalDateTime;

public class Calcular {
    public static String calcularTempo(LocalDateTime dataEntrada){
        if (dataEntrada == null) return "Sem registro";
        LocalDateTime agora = LocalDateTime.now();
        Duration duracao = Duration.between(dataEntrada, agora);
            long horas = duracao.toHours();
            long minutos = duracao.toMinutesPart();
        return String.format("0%dh %02dm", horas, minutos);
        }
    public static double calculaPreco(LocalDateTime dataEntrada){
        long minutosTotais = Duration.between(dataEntrada, LocalDateTime.now()).toMinutes();
        if(minutosTotais <= 30){
            return 50.00;
        } else {
            long minutosExcedentes = minutosTotais - 30;
            long blocosDeCinco = (long) Math.ceil(minutosExcedentes / 5.0);
            return 50.00+ (blocosDeCinco * 1.25);
        }
    }
    }
