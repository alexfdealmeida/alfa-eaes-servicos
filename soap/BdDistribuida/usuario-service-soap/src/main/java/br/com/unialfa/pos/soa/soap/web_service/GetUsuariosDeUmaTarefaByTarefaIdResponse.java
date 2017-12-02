//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.7 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2017.12.02 às 04:45:55 PM BRST 
//


package br.com.unialfa.pos.soa.soap.web_service;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de anonymous complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence maxOccurs="unbounded" minOccurs="0">
 *         &lt;element name="usuariosTarefas" type="{http://soa.pos.unialfa.com.br/soap/web-service}usuarioTarefa"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "usuariosTarefas"
})
@XmlRootElement(name = "getUsuariosDeUmaTarefaByTarefaIdResponse")
public class GetUsuariosDeUmaTarefaByTarefaIdResponse {

    protected List<UsuarioTarefa> usuariosTarefas;

    /**
     * Gets the value of the usuariosTarefas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the usuariosTarefas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUsuariosTarefas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UsuarioTarefa }
     * 
     * 
     */
    public List<UsuarioTarefa> getUsuariosTarefas() {
        if (usuariosTarefas == null) {
            usuariosTarefas = new ArrayList<UsuarioTarefa>();
        }
        return this.usuariosTarefas;
    }

}
