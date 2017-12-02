//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.7 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2017.12.02 às 04:45:55 PM BRST 
//


package br.com.unialfa.pos.soa.soap.web_service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de usuarioTarefa complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="usuarioTarefa">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idUsuarioTarefa" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="usuario" type="{http://soa.pos.unialfa.com.br/soap/web-service}usuario"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "usuarioTarefa", propOrder = {
    "idUsuarioTarefa",
    "usuario"
})
public class UsuarioTarefa {

    protected long idUsuarioTarefa;
    @XmlElement(required = true)
    protected Usuario usuario;

    /**
     * Obtém o valor da propriedade idUsuarioTarefa.
     * 
     */
    public long getIdUsuarioTarefa() {
        return idUsuarioTarefa;
    }

    /**
     * Define o valor da propriedade idUsuarioTarefa.
     * 
     */
    public void setIdUsuarioTarefa(long value) {
        this.idUsuarioTarefa = value;
    }

    /**
     * Obtém o valor da propriedade usuario.
     * 
     * @return
     *     possible object is
     *     {@link Usuario }
     *     
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Define o valor da propriedade usuario.
     * 
     * @param value
     *     allowed object is
     *     {@link Usuario }
     *     
     */
    public void setUsuario(Usuario value) {
        this.usuario = value;
    }

}
