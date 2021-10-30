
package ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para SearchbyCreaDate complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="SearchbyCreaDate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="creaDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchbyCreaDate", propOrder = {
    "creaDate"
})
public class SearchbyCreaDate {

    protected String creaDate;

    /**
     * Obtiene el valor de la propiedad creaDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreaDate() {
        return creaDate;
    }

    /**
     * Define el valor de la propiedad creaDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreaDate(String value) {
        this.creaDate = value;
    }

}
