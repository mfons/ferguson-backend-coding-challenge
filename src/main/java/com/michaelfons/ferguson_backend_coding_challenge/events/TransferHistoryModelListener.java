package com.michaelfons.ferguson_backend_coding_challenge.events;

import com.michaelfons.ferguson_backend_coding_challenge.model.TransferHistory;
import com.michaelfons.ferguson_backend_coding_challenge.services.SequenceGenerator;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class TransferHistoryModelListener extends AbstractMongoEventListener<TransferHistory> {
    private final SequenceGenerator sequenceGenerator;

    public TransferHistoryModelListener(SequenceGenerator sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
   public void onBeforeConvert(BeforeConvertEvent<TransferHistory> event) {
       if (event.getSource().getId().intValue() < 1) {
           event.getSource().setId(BigInteger.valueOf(sequenceGenerator.generateSequence(TransferHistory.SEQUENCE_NAME)));
       }
   }
}
