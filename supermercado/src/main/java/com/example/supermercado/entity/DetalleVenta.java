package com.example.supermercado.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
@Entity 
@Table (name = "DetalleVenta")
public class DetalleVenta {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @Column (name = "cantidad")
    private Integer cantidad;

    @Column (name = "precio_unitario")
    private BigDecimal precioUnitario;

    @Column (name = "sub_total")
    private  BigDecimal subTotal;

    @ManyToOne 
    @JoinColumn (name = "venta_id")
    private Venta venta;

    @ManyToOne 
    @JoinColumn (name = "producto_id")
    private Producto producto;


}
