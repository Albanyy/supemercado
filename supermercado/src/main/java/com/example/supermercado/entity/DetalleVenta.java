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

    @Column (name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column (name = "precio_unitario", nullable = false, precision = 12, scale = 2)
    private BigDecimal precioUnitario;

    @Column (name = "sub_total" , nullable = false, precision = 12, scale = 2)
    private  BigDecimal subTotal;

    @ManyToOne(optional = false)
    @JoinColumn (name = "venta_id", nullable = false)
    private Venta venta;

    @ManyToOne (optional = false)
    @JoinColumn (name = "producto_id", nullable = false)
    private Producto producto;


}
