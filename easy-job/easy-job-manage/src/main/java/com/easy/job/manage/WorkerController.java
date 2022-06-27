package com.easy.job.manage;

import com.easy.job.core.model.Worker;
import com.easy.job.core.service.WorkerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author hkal_mark
 */
/**
 * @author hkal_mark
 */
@RestController
@RequestMapping("/job/manage/worker")
public class WorkerController {
    @Resource
    private WorkerService workerService;

    @PostMapping("insert")
    public ResponseEntity insert(@RequestBody Worker worker) {
        return new ResponseEntity(workerService.insert(worker), HttpStatus.OK);
    }

    /**
     * 禁用
     * @param workerId 节点id
     * @return
     */
    @PostMapping("disable")
    public ResponseEntity disable(Long workerId){
        workerService.disable(workerId);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * 重新启用节点
     * @param workerId 节点id
     * @return
     */
    @PostMapping("enable")
    public ResponseEntity enable(Long workerId){
        workerService.enable(workerId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
