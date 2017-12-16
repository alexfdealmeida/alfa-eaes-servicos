using System;
using System.ComponentModel.DataAnnotations;

namespace Usuario.Services.Api.ViewModels
{
    /// <summary>
    /// Modelo classe usuário
    /// </summary>
    public class UsuarioViewModel
    {
        /// <summary>
        /// Construtor que passa por default Id = 0
        /// </summary>
        public UsuarioViewModel()
        {
            Id = 0;
        }

        /// <summary>
        /// Campo chave
        /// </summary>
        [Key]
        public long Id { get; set; }

        /// <summary>
        /// Propriedade Nome do usuário - Requerido, entre 2 e 255 caracteres
        /// </summary>
        [Required(ErrorMessage = "O Nome é requerido")]
        [MinLength(2, ErrorMessage = "O tamanho minimo do Nome é {1}")]
        [MaxLength(255, ErrorMessage = "O tamanho máximo do Nome é {1}")]
        [Display(Name = "Nome do Usuario")]
        public string Nome { get; set; }

        /// <summary>
        /// Propriedade E-mail, 
        /// </summary>
        [Display(Name = "E-mail")]
        [Required(ErrorMessage = "O e-mail é requerido")]
        [MaxLength(255, ErrorMessage = "O tamanho máximo do Nome é {1}")]
        [EmailAddress(ErrorMessage = "O e-mail não é válido")]
        public string Email { get; set; }

    }
}