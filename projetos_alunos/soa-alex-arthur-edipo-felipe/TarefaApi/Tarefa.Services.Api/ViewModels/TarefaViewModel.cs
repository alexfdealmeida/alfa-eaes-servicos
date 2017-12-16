using System;
using System.ComponentModel.DataAnnotations;

namespace Tarefa.Services.Api.ViewModels
{
    public class TarefaViewModel
    {
        public TarefaViewModel()
        {
            Id = 0;
        }

        [Key]
        public long Id { get; set; }

        [Required(ErrorMessage = "O Título é requerido")]
        [MinLength(2, ErrorMessage = "O tamanho minimo do Título é {1}")]
        [MaxLength(255, ErrorMessage = "O tamanho máximo do Título é {1}")]
        [Display(Name = "Título da Tarefa")]
        public string Titulo { get; set; }


        [Display(Name = "Descrição")]
        [MaxLength(255, ErrorMessage = "O tamanho máximo da Descrição é {1}")]
        public string Descricao { get; set; }

        [Display(Name = "Início")]
        public DateTime? Inicio { get; set; }

        public DateTime? Encerramento { get; set; }

    }
}