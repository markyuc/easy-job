package com.easy.job.manage;

import com.easy.job.core.model.Group;
import com.easy.job.core.service.GroupService;
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
@RequestMapping("/job/manage/group")
public class GroupController {
    @Resource
    private GroupService groupService;

    @PostMapping("insert")
    public ResponseEntity insert(@RequestBody Group group) {
        return new ResponseEntity(groupService.insert(group), HttpStatus.OK);
    }

    @PostMapping("query")
    public ResponseEntity query(@RequestBody Long groupId) {
        return new ResponseEntity(groupService.query(groupId), HttpStatus.OK);
    }
}
