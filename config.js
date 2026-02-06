module.exports = {
  $schema: "https://docs.renovatebot.com/renovate-schema.json",

  // REQUIRED
  extends: ["config:base"],

  // Make PRs immediately (no waiting)
  prCreation: "immediate",

  // Explicitly disable dry-run
  dryRun: false,

  // Helpful logging while testing
  logLevel: "info",

  // Optional but recommended
  dependencyDashboard: true,

  // Keep branches clean
  rebaseWhen: "behind-base-branch",

  // Group example (customize later)
  packageRules: [
    {
      matchPackagePatterns: ["^org.springframework"],
      groupName: "spring"
    }
  ]
};
