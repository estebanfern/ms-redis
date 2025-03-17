package com.esteban.ms.clientes.listener;

import com.esteban.ms.clientes.service.ClienteService;
import com.esteban.ms.common.constant.Channel;
import com.esteban.ms.common.exception.MSException;
import com.esteban.ms.common.listener.AbstractListener;
import com.esteban.ms.common.model.GetClienteTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GetClienteListener extends AbstractListener<GetClienteTask> {

    private final ClienteService clienteService;

    public GetClienteListener(ClienteService clienteService) {
        super();
        this.clienteService = clienteService;
    }

    @Override
    protected Class<GetClienteTask> getTaskClass() {
        return GetClienteTask.class;
    }

    @Override
    public String getChannel() {
        return Channel.GET_CLIENTE;
    }

    @Override
    public GetClienteTask process(GetClienteTask task) throws MSException {
        task.setResult(clienteService.findById(task.getData()));
        return task;
    }

}
