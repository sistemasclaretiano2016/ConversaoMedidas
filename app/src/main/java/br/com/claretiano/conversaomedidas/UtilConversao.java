package br.com.claretiano.conversaomedidas;

public class UtilConversao {

    public static double ConverterComprimento(ComprimentoEnum de, ComprimentoEnum para, double valor) {

        if (ComprimentoEnum.Metro.equals(de)) {
            if (ComprimentoEnum.Polegada.equals(para)) {
                return valor * 39.3701;
            } else if (ComprimentoEnum.Quilometro.equals(para)) {
                return valor * 0.001;
            }
        } else if (ComprimentoEnum.Polegada.equals(de)) {
            if (ComprimentoEnum.Metro.equals(para)) {
                return valor * 0.0254;
            } else if (ComprimentoEnum.Quilometro.equals(para)) {
                return valor * 0.0000254;
            }
        } else if (ComprimentoEnum.Quilometro.equals(de)) {
            if (ComprimentoEnum.Metro.equals(para)) {
                return valor * 1000;
            } else if (ComprimentoEnum.Polegada.equals(para)) {
                return valor * 39370.1;
            }
        }
        return valor;
    }

    public static double ConverterPeso(PesoEnum de, PesoEnum para, double valor) {

        if (PesoEnum.Grama.equals(de)) {
            if (PesoEnum.Tonelada.equals(para)) {
                return valor * 0.000001;
            } else if (PesoEnum.Quilograma.equals(para)) {
                return valor * 0.001;
            }
        } else if (PesoEnum.Quilograma.equals(de)) {
            if (PesoEnum.Grama.equals(para)) {
                return valor * 1000;
            } else if (PesoEnum.Tonelada.equals(para)) {
                return valor * 0.001;
            }
        } else if (PesoEnum.Tonelada.equals(de)) {
            if (PesoEnum.Grama.equals(para)) {
                return valor * 1000000;
            } else if (PesoEnum.Quilograma.equals(para)) {
                return valor * 1000;
            }
        }
        return valor;
    }

    public static double ConverterTemperatura(TemperaturaEnum de, TemperaturaEnum para, double valor) {

        if (TemperaturaEnum.Celsius.equals(de)) {
            if (TemperaturaEnum.Fahrenheit.equals(para)) {
                return (valor * 1.8) + 32;
            }
        } else if (TemperaturaEnum.Fahrenheit.equals(de)) {
            if (TemperaturaEnum.Celsius.equals(para)) {
                return (valor - 32) / 1.8;
            }
        }
        return valor;
    }


    public enum ComprimentoEnum {
        None, Metro, Polegada, Quilometro;

        public static ComprimentoEnum retornaPorLegenda(String texto) {
            if ("Metro".equals(texto)) {
                return ComprimentoEnum.Metro;
            } else if ("Polegada".equals(texto)) {
                return ComprimentoEnum.Polegada;
            } else if ("Quilometro".equals(texto)) {
                return ComprimentoEnum.Quilometro;
            }
            return ComprimentoEnum.None;
        }

    }

    public enum PesoEnum {
        None, Grama, Quilograma, Tonelada;

        public static PesoEnum retornaPorLegenda(String texto) {
            if ("Grama".equals(texto)) {
                return PesoEnum.Grama;
            } else if ("Quilograma".equals(texto)) {
                return PesoEnum.Quilograma;
            } else if ("Tonelada".equals(texto)) {
                return PesoEnum.Tonelada;
            }
            return PesoEnum.None;
        }
    }

    public enum TemperaturaEnum {None, Celsius, Fahrenheit;

        public static TemperaturaEnum retornaPorLegenda(String texto) {
            if ("Celsius".equals(texto)) {
                return TemperaturaEnum.Celsius;
            } else if ("Fahrenheit".equals(texto)) {
                return TemperaturaEnum.Fahrenheit;
            }
            return TemperaturaEnum.None;
        }
    }


}
