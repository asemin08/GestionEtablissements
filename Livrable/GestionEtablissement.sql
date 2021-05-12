SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `GestionEtablissement`
--

-- --------------------------------------------------------

--
-- Structure de la table `Course`
--

CREATE TABLE `Course` (
  `id` int NOT NULL,
  `coursesubject` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `nbhours` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `Course_Person`
--

CREATE TABLE `Course_Person` (
  `idPerson` int NOT NULL,
  `idCourse` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `Mark`
--

CREATE TABLE `Mark` (
  `id` int NOT NULL,
  `idStudent` int DEFAULT NULL,
  `idCourse` int DEFAULT NULL,
  `mark` float DEFAULT NULL,
  `assessment` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=COMPACT;

-- --------------------------------------------------------

--
-- Structure de la table `Person`
--

CREATE TABLE `Person` (
  `id` int NOT NULL,
  `firstname` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `lastname` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `role` int NOT NULL DEFAULT '4',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `dateofbirth` date DEFAULT NULL,
  `subjecttaught` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `average` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `Person`
--

INSERT INTO `Person` (`id`, `firstname`, `lastname`, `email`, `address`, `phone`, `role`, `password`, `dateofbirth`, `subjecttaught`, `average`) VALUES
(1, 'directeur', 'directeur', 'directeur', 'directeur ', '0000000000', 1, 'directeur', '2020-01-23', NULL, NULL),
(2, 'manager', 'manager', 'manager', 'manager', '0000000000', 2, 'manager', '2020-12-22', NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `School`
--

CREATE TABLE `School` (
  `id` int NOT NULL,
  `surname` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `director` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `School`
--

INSERT INTO `School` (`id`, `surname`, `email`, `address`, `phone`, `director`) VALUES
(4, 'Ensup', 'ensup@gmail.com', '1 bis Avenue du 8 mai 1945', '0161380575', 1);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `Course`
--
ALTER TABLE `Course`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `Course_Person`
--
ALTER TABLE `Course_Person`
  ADD PRIMARY KEY (`idPerson`,`idCourse`),
  ADD KEY `fk_person_course` (`idCourse`);

--
-- Index pour la table `Mark`
--
ALTER TABLE `Mark`
  ADD PRIMARY KEY (`id`),
  ADD KEY `kMarkCourse` (`idCourse`),
  ADD KEY `kMarkStudent` (`idStudent`);

--
-- Index pour la table `Person`
--
ALTER TABLE `Person`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Index pour la table `School`
--
ALTER TABLE `School`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_person_school` (`director`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `Course`
--
ALTER TABLE `Course`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=102;

--
-- AUTO_INCREMENT pour la table `Mark`
--
ALTER TABLE `Mark`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT pour la table `Person`
--
ALTER TABLE `Person`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=208;

--
-- AUTO_INCREMENT pour la table `School`
--
ALTER TABLE `School`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `Course_Person`
--
ALTER TABLE `Course_Person`
  ADD CONSTRAINT `fk_course_person` FOREIGN KEY (`idPerson`) REFERENCES `Person` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_person_course` FOREIGN KEY (`idCourse`) REFERENCES `Course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `Mark`
--
ALTER TABLE `Mark`
  ADD CONSTRAINT `kMarkCourse` FOREIGN KEY (`idCourse`) REFERENCES `Course` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `kMarkStudent` FOREIGN KEY (`idStudent`) REFERENCES `Person` (`id`) ON UPDATE CASCADE;

--
-- Contraintes pour la table `School`
--
ALTER TABLE `School`
  ADD CONSTRAINT `fk_person_school` FOREIGN KEY (`director`) REFERENCES `Person` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
