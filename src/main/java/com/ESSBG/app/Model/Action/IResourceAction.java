package com.ESSBG.app.Model.Action;

import com.ESSBG.app.Model.ResourceEnum;

import java.util.List;

/**
 * Author: Sebastian Selander
 * 
 * An interface that represent an action that give resources.
 */
public interface IResourceAction {
    List<ResourceEnum> getList();
}
