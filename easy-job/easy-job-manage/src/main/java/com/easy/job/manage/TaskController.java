package com.easy.job.manage;

import com.easy.job.core.model.Task;
import com.easy.job.core.service.TaskService;
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

@RestController
@RequestMapping("/job/manager/task")
public class TaskController {
    @Resource
    private TaskService taskService;

    @PostMapping("register")
    public ResponseEntity insert(@RequestBody Task task, Long groupId) {
        return new ResponseEntity(taskService.register(task, groupId), HttpStatus.OK);
    }

    /**
     * 禁用
     * @param id 节点id
     * @return
     */
    @PostMapping("disable")
    public ResponseEntity disable(Long id){
        taskService.disable(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * 重新启用
     * @param id 任务id
     * @return
     */
    @PostMapping("enable")
    public ResponseEntity enable(Long id){
        taskService.enable(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * 重新启用
     * @param id 任务id
     * @return
     */
    @PostMapping("retry")
    public ResponseEntity retry(Long id){
        taskService.retry(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
