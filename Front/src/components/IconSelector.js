import React from "react";
import { ReactComponent as Cpu } from '../icons/066-cpu-2.svg';
import { ReactComponent as Cooler } from '../icons/046-cooler.svg';
import { ReactComponent as Motherboard } from '../icons/070-motherboard.svg';
import { ReactComponent as Ram } from '../icons/088-ram-memory.svg';
import { ReactComponent as Storage } from '../icons/089-hard-drive.svg';
import { ReactComponent as Gpu } from '../icons/087-graphic-card.svg';
import { ReactComponent as Case } from '../icons/091-desktop.svg';
import { ReactComponent as Psu } from '../icons/084-supply.svg';
import { ReactComponent as Computer } from '../icons/007-computer.svg';

const iconTypes = {
  cpu: Cpu,
  cooler: Cooler,
  mb: Motherboard,
  ram: Ram,
  storage: Storage,
  gpu: Gpu,
  case: Case,
  psu: Psu,
  computer: Computer
};

const IconComponent = ({ name, ...props }) => {
  let Icon = iconTypes[name];
  return <Icon {...props} />;
};

export default IconComponent;