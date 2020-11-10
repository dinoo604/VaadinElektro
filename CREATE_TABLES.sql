 
CREATE TABLE `student` (
  `studentennummer` int(10) NOT NULL,
  `naam` varchar(20) DEFAULT NULL,
  `achternaam` varchar(20) DEFAULT NULL,
  `cohort` int(11) DEFAULT NULL,
  `afstudeer_jaar` int(11) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `geslacht` char(1) DEFAULT NULL,
  `orientatie` char(1) DEFAULT NULL,
  `studie_status` char(1) DEFAULT NULL,
  PRIMARY KEY (`studentennummer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `vak` (
  `idvak` int(11) NOT NULL,
  `vak_naam` varchar(80) DEFAULT NULL,
  `studiepunten` float DEFAULT NULL,
  `semester` int(11) DEFAULT NULL,
  `vak_orientatie` char(1) DEFAULT NULL,
  PRIMARY KEY (`idvak`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `student_maakt_vak` (
  `student_studentennummer` int(10) NOT NULL,
  `vak_idvak` int(11) NOT NULL,
  `periode` char(1) NOT NULL,
  `jaar` int(11) NOT NULL,
  `cijfer` float DEFAULT NULL,
  PRIMARY KEY (`student_studentennummer`,`vak_idvak`,`periode`,`jaar`),
  KEY `fk_student_has_vak_vak1_idx` (`vak_idvak`),
  KEY `fk_student_has_vak_student_idx` (`student_studentennummer`),
  CONSTRAINT `fk_student_has_vak_student` FOREIGN KEY (`student_studentennummer`) REFERENCES `student` (`studentennummer`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_student_has_vak_vak1` FOREIGN KEY (`vak_idvak`) REFERENCES `vak` (`idvak`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
