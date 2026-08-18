package com.nitech.cards.audit;

import org.springframework.data.domain.AuditorAware;

import javax.swing.text.html.Option;
import java.util.Optional;

public class AuditAwareImpl implements AuditorAware<String> {

        public Optional<String> getCurrentAuditor(){
            return Optional.of("CARDS_MS");
        }


}
