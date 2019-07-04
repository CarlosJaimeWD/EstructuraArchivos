/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import static java.lang.System.in;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author 7053
 */
public class ProductoDTO implements Serializable{
    
    private static final Logger LOG = Logger.getLogger(ProductoDTO.class.getName());
    public static final int longitud=150+Long.BYTES+Integer.BYTES+Integer.BYTES+Integer.BYTES+Integer.BYTES+22;
    
    private Long id;
    private StringBuffer cpu;
    private StringBuffer motherboard;
    private Integer ram;
    private StringBuffer gpu;
    private Integer psu;
    private StringBuffer gabinete;
    private Integer monitores;
    private Integer precio;

    public ProductoDTO() {
        setId(0L);
        setCpu(new StringBuffer(""));
        setMotherboard(new StringBuffer(""));
        setRam(000);
        setGpu(new StringBuffer(""));
        setPsu(0000);
        setGabinete(new StringBuffer(""));
        setMonitores(0);
        setPrecio(00);
    }

    public ProductoDTO(Long id, StringBuffer cpu, StringBuffer motherboard, Integer ram, StringBuffer gpu, Integer psu, StringBuffer gabinete, Integer monitores, Integer precio) {
        setId(id);
        setCpu(cpu);
        setMotherboard(motherboard);
        setRam(ram);
        setGpu(gpu);
        setPsu(psu);
        setGabinete(gabinete);
        setMonitores(monitores);
        setPrecio(precio);
    }
    
    /*
       System.out.println(registro);
        String str = registro;
        String answer = str.substring(str.indexOf("{")+1,str.indexOf("}"));
        System.out.println(answer);
        registro = answer;
    */
    
    public ProductoDTO(String registro){
        //System.out.println(registro);
       // registro=registro.substring(3);
        //registro=registro.replace("}", "");
        if (registro != null) {
            registro = registro.substring(registro.indexOf("{")+1, registro.indexOf("}"));
            String datos[] = registro.split(",");
            System.out.println("id: " + datos[0]);
            if (!datos[0].equals("000")) {
                id = Long.parseLong(datos[0]);
            cpu = new StringBuffer(datos[1].trim());
            motherboard = new StringBuffer(datos[2].trim());
            ram = Integer.parseInt(datos[3]);
            gpu = new StringBuffer(datos[4].trim());
            psu = Integer.parseInt(datos[5]);
            gabinete = new StringBuffer(datos[6].trim());
            monitores = Integer.parseInt(datos[7]);
            precio = Integer.parseInt((datos[8]));
            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StringBuffer getCpu() {
        return cpu;
    }

    public void setCpu(StringBuffer cpu) {
        this.cpu = cpu;
        //this.cpu.setLength(15);
        for (int i = cpu.length(); i < 15; i++) {
            this.cpu.append(" ");
        }
    }

    public StringBuffer getMotherboard() {
        return motherboard;
    }

    public void setMotherboard(StringBuffer motherboard) {
        this.motherboard = motherboard;
        //this.motherboard.setLength(20);
        for (int i = motherboard.length(); i < 20; i++) {
            this.motherboard.append(" ");
        }
    }

    public Integer getRam() {
        return ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    public StringBuffer getGpu() {
        return gpu;
    }

    public void setGpu(StringBuffer gpu) {
        this.gpu = gpu;
        //this.gpu.setLength(20);
        for (int i = gpu.length(); i < 20; i++) {
            this.gpu.append(" ");
        }
    }

    public Integer getPsu() {
        return psu;
    }

    public void setPsu(Integer psu) {
        this.psu = psu;
    }

    public StringBuffer getGabinete() {
        return gabinete;
    }

    public void setGabinete(StringBuffer gabinete) {
        this.gabinete = gabinete;
        //this.gabinete.setLength(20);
        for (int i = gabinete.length(); i < 20; i++) {
            this.gabinete.append(" ");
        }
    }

    public Integer getMonitores() {
        return monitores;
    }

    public void setMonitores(Integer monitores) {
        this.monitores = monitores;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProductoDTO other = (ProductoDTO) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProductoDTO{" + "id=" + id + ", cpu=" + cpu + ", motherboard=" + motherboard + ", ram=" + ram + ", gpu=" + gpu + ", psu=" + psu + ", gabinete=" + gabinete + ", monitores=" + monitores + ", precio=" + precio + '}';
    }
    
    public String toStringFile(){
        String idS=""+id;
        String ramS = ""+ram;
        String psuS = ""+psu;
        String monitoresS = ""+monitores;
        String precioS=""+precio;
        switch(idS.length()){
            case 1: idS="00"+id; break;
            case 2: idS="0"+id; break;            
        }
        switch(ramS.length()){
            case 1: ramS="00"+ram; break;
            case 2: ramS="0"+ram; break;            
        }
        switch(psuS.length()){
            case 1: psuS="000"+psu; break;
            case 2: psuS="00"+psu; break;    
            case 3: psuS="0"+psu; break;    
        }

            switch(precioS.length()){
            case 1: precioS="0"+precio; break;
        }
        return "{" + idS + "," + cpu + "," + motherboard + "," + ramS + "," + gpu + "," + psuS + "," + gabinete + "," + monitoresS + "," + precioS + "}\n";
    }
    
    
    
}
