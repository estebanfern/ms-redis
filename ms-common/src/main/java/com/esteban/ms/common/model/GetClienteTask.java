package com.esteban.ms.common.model;

import com.esteban.ms.common.entity.Cliente;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GetClienteTask extends Task<Long, Cliente> {

    public GetClienteTask() {
        super();
    }

}
