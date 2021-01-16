# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure("2") do |config|

  config.vm.box = "ubuntu/focal64"

  config.vm.network "forwarded_port", guest: 80, host: 8080
  config.vm.network "forwarded_port", guest: 8888, host: 8888
  config.vm.network "forwarded_port", guest: 8886, host: 8886
  config.vm.network "forwarded_port", guest: 5432, host: 5434

  config.vm.provider "virtualbox" do |vb|
    vb.memory = 1024
    vb.cpus = 2
  end

  config.vm.define "crs_auth_service" do |dev|
    dev.vm.provision "ansible" do |ansible|
      ansible.playbook = "provisioning/dev.yml"
      ansible.verbose = "vv"
    end
  end

end
