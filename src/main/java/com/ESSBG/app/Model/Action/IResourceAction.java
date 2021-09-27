package com.ESSBG.app.Model.Action;

import com.ESSBG.app.Model.ResourceEnum;

import java.util.List;

public interface IResourceAction {

    List<ResourceEnum> getResources(); //maybe parameterize this one over science and resources

}
