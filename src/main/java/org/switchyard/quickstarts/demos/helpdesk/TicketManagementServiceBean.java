package org.switchyard.quickstarts.demos.helpdesk;

import org.apache.log4j.Logger;
import org.switchyard.component.bean.Service;

@Service(TicketManagementService.class)
public class TicketManagementServiceBean implements TicketManagementService {

    private static final Logger LOGGER = Logger.getLogger(TicketManagementServiceBean.class);

    @Override
    public TicketAck openTicket(final Ticket ticket) {
        log("opening ticket");
        ticket.setStatus("opened");
        return new TicketAck().setId(ticket.getId()).setReceived(true);
    }

    @Override
    public void approveTicket(final Ticket ticket) {
        log("approving ticket");
        ticket.setStatus("approved");
    }

    @Override
    public void closeTicket(final Ticket ticket) {
        log("closing ticket");
        ticket.setStatus("closed");
    }

    @Override
    public void requestDetails(final Ticket ticket) {
        log("requesting details");
        ticket.setStatus("requested");
    }

    @Override
    public void rejectTicket(final Ticket ticket) {
        log("rejecting ticket");
        ticket.setStatus("rejected");
    }

    private void log(final String msg) {
        LOGGER.info("********** " + msg + " **********");
    }

}
