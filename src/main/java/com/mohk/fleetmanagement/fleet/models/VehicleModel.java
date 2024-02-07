package com.mohk.fleetmanagement.fleet.models;

import com.mohk.fleetmanagement.settings.models.CommonObject;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class VehicleModel extends CommonObject {
}
