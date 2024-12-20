package ar.edu.utn.frc.tup.lc.iv.records;

public record ResultadoRecord(  Long id,
                                String eleccionTipo,
                                String recuentoTipo,
                                String padronTipo,
                                Long distritoId,
                                String distritoNombre,
                                Long seccionProvincialId,
                                String seccionProvincialNombre,
                                Long seccionId,
                                String seccionNombre,
                                String circuitoId,
                                String circuitoNombre,
                                Long mesaId,
                                String mesaTipo,
                                Integer mesaElectores,
                                Long cargoId,
                                String cargoNombre,
                                Long agrupacionId,
                                String agrupacionNombre,
                                String listaNumero,
                                String listaNombre,
                                String votosTipo,
                                Integer votosCantidad,
                                Integer año) {
}
