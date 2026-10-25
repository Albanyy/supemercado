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
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "El nombre del producto no puede estar vacío")
    @Size(max = 100, message = "El nombre del producto no puede tener más de 100 caracteres")
    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @NotNull(message = "El precio del producto no puede estar vacío")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio del producto debe ser mayor que cero")
    @Column(name = "precio")
    private BigDecimal precio;

    @NotNull(message = "El stock del producto no puede estar vacío")
    @PositiveOrZero(message = "El stock del producto no puede ser negativo")
    @Column(name = "stock")
    private Integer stock;

    @NotBlank(message = "El código de barras del producto no puede estar vacío")
    @Size (max = 50, message = "El código de barras del producto no puede tener más de 50 caracteres")
    @Column(name = "codigo_barras", length = 50, nullable = false, unique = true)
    private String codigoBarras;

    @NotNull(message = "La categoría del producto no puede estar vacía")
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @NotNull(message = "El proveedor del producto no puede estar vacío")
    @ManyToOne
    @JoinColumn(name = "proveedor_id")
    private Proveedor proveedor;

}
