package org.switchyard.quickstarts.demos.helpdesk;

import java.io.StringReader;

import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamSource;

import org.switchyard.annotations.Transformer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Transformers {

    @Transformer(from = "{urn:switchyard-quickstart-demo:helpdesk:1.0}openTicket")
    public Ticket transform(final Element from) {
        final Ticket ticket = new Ticket();
        ticket.setId(getElementValue(from, "id"));
        return ticket;
    }

    @Transformer(to = "{urn:switchyard-quickstart-demo:helpdesk:1.0}openTicketResponse")
    public Element transformToElement(final TicketAck ticketAck) {
        final StringBuilder ackXml = new StringBuilder()
            .append("<helpdesk:openTicketResponse xmlns:helpdesk=\"urn:switchyard-quickstart-demo:helpdesk:1.0\">")
            .append("<ticketAck>")
            .append("<id>" + ticketAck.getId() + "</id>")
            .append("<received>" + ticketAck.isReceived() + "</received>")
            .append("</ticketAck>")
            .append("</helpdesk:openTicketResponse>");
        return toElement(ackXml.toString());
    }

    @Transformer
    public Ticket transformToTicket(final TicketAck ticketAck) {
        final Ticket ticket = new Ticket();
        ticket.setId(ticketAck.getId());
        return ticket;
    }

    private String getElementValue(final Element parent, final String elementName) {
        String value = null;
        final NodeList nodes = parent.getElementsByTagName(elementName);
        if (nodes.getLength() > 0) {
            value = nodes.item(0).getChildNodes().item(0).getNodeValue();
        }
        return value;
    }

    private Element toElement(final String xml) {
        final DOMResult dom = new DOMResult();
        try {
            TransformerFactory.newInstance().newTransformer().transform(new StreamSource(new StringReader(xml)), dom);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return ((Document) dom.getNode()).getDocumentElement();
    }

}
