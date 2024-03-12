package io.itch.padjokej.tealeaf.client;

import io.itch.padjokej.tealeaf.registry.PacketsRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.mob.PatrolEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.Vec3d;

public class TealeafClient implements ClientModInitializer {
    public static void createTeapotParticle(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender)
    {
        double x = buf.readDouble();
        double y = buf.readDouble();
        double z = buf.readDouble();
        client.particleManager.addParticle(ParticleTypes.SMOKE, x, y, z, 0, 0.05, 0);
    }

    @Override
    public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(
                PacketsRegistry.BOIL_PARTICLE_PACKET,
                TealeafClient::createTeapotParticle
        );
    }

}
