package com.example.supermercado.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor 
@Entity 
@Table (name = "Proveedor")
public class Proveedor {

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @NotBlank(message = "El nombre del proveedor no puede estar vacío")
    @Size (max = 100, message = "El nombre del proveedor no puede tener más de 100 caracteres")
    @Column (nullable = false, length = 100, name = "nombre")
    private String nombre;

    @Size (max = 20, message = "El teléfono no puede tener más de 20 caracteres")
    @Column (name = "telefono", length = 20)
    private String telefono;

    @NotBlank (message = "El correo del proveedor no puede estar vacío")
    @Email (message = "El correo debe ser valido")
    @Size (max = 150, message = "El correo del proveedor no puede tener más de 150 caracteres")
    @Column (nullable = false, length = 150, name = "correo")
    private String correo;
}
